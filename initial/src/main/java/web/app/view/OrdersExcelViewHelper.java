package web.app.view;

import businessdomain.Cheque;
import businessdomain.Customer;
import businessdomain.DomainHelper;
import businessdomain.Orders;
import businessdomain.Pizza;
import infrastructure.date.converters.SimpleDateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

import static web.app.view.ExcelView.createAndPopulateCell;
import static web.app.view.ExcelView.createHeaderStyle;

public class OrdersExcelViewHelper {

    private static final String DELIMITER = ", ";
    private static final String PRICE_FORMAT_STRING = "%.2f";

    static int populateSingleOrdersFunction(Pair<HSSFWorkbook, Map<String, Object>> workbookAndModel) {
        Map<String, Object> model = workbookAndModel.getRight();
        Orders orders = (Orders) model.get(DomainHelper.ORDERS);
        return populateOrdersList(workbookAndModel.getLeft(), Collections.singletonList(orders));
    }

    static int populateOrdersListFunction(Pair<HSSFWorkbook, Map<String, Object>> workbookAndModel) {
        Map<String, Object> model = workbookAndModel.getRight();
        List<Orders> ordersList = (List<Orders>) model.get(DomainHelper.ORDERSLIST);
        return populateOrdersList(workbookAndModel.getLeft(), ordersList);
    }

    static int populateOrdersList(HSSFWorkbook workbook, List<Orders> ordersList) {
        CellStyle style = createHeaderStyle(workbook);
        Sheet sheet = workbook.createSheet(ExcelView.SHEET_NAME);
        int rowCount = 0;
        int colCount = 0;

        // Create header cells
        Row row = sheet.createRow(rowCount++);
        createAndPopulateCell(row, colCount++, style, DomainHelper.ORDER_ID);
        createAndPopulateCell(row, colCount++, style, DomainHelper.ORDER_DATETIME);
        createAndPopulateCell(row, colCount++, style, DomainHelper.CHEQUE_TITLE);
        createAndPopulateCell(row, colCount++, style, DomainHelper.CUSTOMER_INFO);
        createAndPopulateCell(row, colCount++, style, DomainHelper.ORDER_ITEMS);
        createAndPopulateCell(row, colCount++, style, DomainHelper.ORDER_SUM);

        for (int i = 0; i < ordersList.size(); i++) {
            colCount = createOrdersDataRow(ordersList.get(i), sheet, rowCount + i);
        }
        return colCount + 1;
    }

    static int createOrdersDataRow(Orders orders, Sheet sheet, int rowCount) {

        int colCount = 0;
        // Create data cells
        Row row = sheet.createRow(rowCount);
        String ordersId = String.valueOf(orders.getId());
        Optional<Cheque> cheque = Optional.ofNullable(orders.getCheque());
        String chequeDate = cheque.map(Cheque::getDate)
                .map(SimpleDateConverter::localDateTimeToString).orElse(StringUtils.EMPTY);
        String chequeTitle = cheque.map(Cheque::getTitle).orElse(StringUtils.EMPTY);
        String pizzaMapItems = getPizzaMapItems(orders.getPizzaMap());
        String customerInfo = getCustomerInfo(orders.getCustomer());
        String ordersSum = cheque.map(Cheque::getTotalSum).map(String::valueOf).orElse(StringUtils.EMPTY);
        createAndPopulateCell(row, colCount++, null, ordersId);
        createAndPopulateCell(row, colCount++, null, chequeDate);
        createAndPopulateCell(row, colCount++, null, chequeTitle);
        createAndPopulateCell(row, colCount++, null, customerInfo);
        createAndPopulateCell(row, colCount++, null, pizzaMapItems);
        createAndPopulateCell(row, colCount++, null, ordersSum);
        return colCount;
    }

    private static String getCustomerInfo(Customer customer) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        Optional<Customer> customerOptional = Optional.ofNullable(customer);
        String customerName = customerOptional.map(Customer::getName).orElse(StringUtils.EMPTY);
        String customerEmail = customerOptional.map(Customer::getEmail).orElse(StringUtils.EMPTY);
        stringJoiner.add(customerName).add(customerEmail);
        return stringJoiner.toString();
    }

    private static String getPizzaMapItems(Map<Pizza, Integer> pizzaMap) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        pizzaMap.forEach((pizza, quantity) -> getPizzaMapItemRow(pizza, quantity, stringJoiner));
        return stringJoiner.toString();
    }

    private static void getPizzaMapItemRow(Pizza pizza, Integer quantity, StringJoiner stringJoiner) {
        String itemSum = String.format(PRICE_FORMAT_STRING, pizza.getPrice() * quantity);
        String itemRow = pizza.getName() + " x " + quantity + " = " + itemSum;
        stringJoiner.add(itemRow);
    }

}
