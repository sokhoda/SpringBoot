package web.app.view;

import exceptions.ViewNotFoundException;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import web.infrastructure.Routes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;


public class ExcelView extends AbstractExcelView {
    static final String SHEET_NAME = "sheet 1";
    private String viewName;

    public ExcelView(String viewName) {
        this.viewName = viewName;
    }

    public ExcelView() {
    }

    @Override
    public void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer colCount = Optional.ofNullable(resolveWorkbookPopulationFunction())
                .map(workbookPopulationFunction -> workbookPopulationFunction.apply(Pair.of(workbook, model)))
                .orElseThrow(() -> new ViewNotFoundException("View not found: " + viewName));
        for (int i = 0; i < colCount; i++) {
            workbook.getSheet(SHEET_NAME).autoSizeColumn(i, true);
        }
    }

    private Function<Pair<HSSFWorkbook, Map<String, Object>>, Integer> resolveWorkbookPopulationFunction() {
        switch (viewName) {
            case Routes.PIZZA_EDIT_PAGE:
                return PizzaExcelViewHelper::populateSinglePizzaFunction;
            case Routes.PIZZA_LIST_PAGE:
                return PizzaExcelViewHelper::populatePizzaListFunction;
            case Routes.ORDER_EDIT_PAGE:
                return OrdersExcelViewHelper::populateSingleOrdersFunction;
            case Routes.ORDER_LIST_PAGE:
                return OrdersExcelViewHelper::populateOrdersListFunction;
            default:
                return null;
        }
    }

    public static void createAndPopulateCell(Row row, int columnNumber, CellStyle style, String value) {
        Assert.notNull(row, "Row can not be null");
        Cell cell = row.createCell(columnNumber);
        if (style != null) {
            cell.setCellStyle(style);
        }
        cell.setCellValue(value);
    }

    public static CellStyle createHeaderStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
    }

}
