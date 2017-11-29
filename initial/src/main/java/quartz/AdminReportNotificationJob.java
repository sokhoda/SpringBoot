package quartz;

import businessdomain.DomainHelper;
import businessdomain.Orders;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import pizzaservice.CustomMailService;
import pizzaservice.OrderService;
import web.app.dto.OrdersList;
import web.app.view.ExcelView;
import web.infrastructure.Routes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AdminReportNotificationJob extends QuartzJobBean {
    private static final String SYSTEM_TEMP_DIR_PROPERTY_NAME = "java.io.tmpdir";
    private static final String ORDER_REPORT_FILENAME = "order_report";
    private static final String EXCEL_FILE_EXTENSION = ".xls";
    private static final String SYSTEM_TEMP_DIR_PATH = System.getProperty(SYSTEM_TEMP_DIR_PROPERTY_NAME);
    private int timeout;
    private String adminEmail;
    private ExcelView view = new ExcelView(Routes.ORDER_LIST_PAGE);

    private OrderService orderService;
    private CustomMailService customMailService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        //generate report
        List<Orders> ordersInTheLastWeek = findOrdersInTheLastWeek();
        File report = generateAndSaveReportFile(ordersInTheLastWeek, SYSTEM_TEMP_DIR_PATH);
        //send email to Admin
        customMailService.sendMail(report, adminEmail);
    }

    private List<Orders> findOrdersInTheLastWeek() {
        LocalDateTime fromDateTime = LocalDate.now().minusWeeks(1).atStartOfDay();
        LocalDateTime toDateTime = LocalDateTime.now();
        return orderService.findByDateBetween(fromDateTime, toDateTime);
    }

    private File generateAndSaveReportFile(List<Orders> ordersList, String tempDir) throws JobExecutionException {
        File reportFile = createReportFile(tempDir);
        Map<String, Object> model = createModelWithData(ordersList);

        try (FileOutputStream fos = new FileOutputStream(reportFile)) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            view.buildExcelDocument(model, workbook, null, null);
            workbook.write(fos);
            return reportFile;
        } catch (Exception ex) {
            throw new JobExecutionException("Error while generating workbook", ex);
        }
    }

    private Map<String, Object> createModelWithData(List<Orders> ordersList) {
        Map<String, Object> model = Maps.newHashMap();
        model.put(DomainHelper.ORDERSLIST, new OrdersList(ordersList));
        return model;
    }

    private File createReportFile(String tempDir) throws JobExecutionException {
        try {
            return File.createTempFile(ORDER_REPORT_FILENAME, EXCEL_FILE_EXTENSION, new File(tempDir));
        } catch (IOException ex) {
            throw new JobExecutionException("Report file could not be created", ex);
        }
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setCustomMailService(CustomMailService customMailService) {
        this.customMailService = customMailService;
    }
}
