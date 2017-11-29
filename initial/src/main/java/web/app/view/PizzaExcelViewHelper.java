package web.app.view;

import businessdomain.DomainHelper;
import businessdomain.Pizza;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static web.app.view.ExcelView.createAndPopulateCell;
import static web.app.view.ExcelView.createHeaderStyle;

public class PizzaExcelViewHelper {

    static int populatePizzaListFunction(Pair<HSSFWorkbook, Map<String, Object>> workbookAndModel) {
        Map<String, Object> model = workbookAndModel.getRight();
        List<Pizza> pizzaList = (List<Pizza>) model.get(DomainHelper.PIZZALIST);
        return populatePizzaList(workbookAndModel.getLeft(), pizzaList);
    }

    static int populatePizzaList(HSSFWorkbook workbook, List<Pizza> pizzaList) {
        CellStyle style = createHeaderStyle(workbook);
        Sheet sheet = workbook.createSheet(ExcelView.SHEET_NAME);
        int rowCount = 0;
        int colCount = 0;

        // Create header cells
        Row row = sheet.createRow(rowCount++);
        createAndPopulateCell(row, colCount++, style, DomainHelper.PIZZA_ID);
        createAndPopulateCell(row, colCount++, style, DomainHelper.PIZZA_NAME);
        createAndPopulateCell(row, colCount++, style, DomainHelper.PIZZA_TYPE);
        createAndPopulateCell(row, colCount, style, DomainHelper.PIZZA_PRICE);

        for (int i = 0; i < pizzaList.size(); i++) {
            colCount = createPizzaDataRow(pizzaList.get(i), sheet, rowCount + i);
        }
        return colCount + 1;
    }

    static int populateSinglePizzaFunction(Pair<HSSFWorkbook, Map<String, Object>> workbookAndModel) {
        Map<String, Object> model = workbookAndModel.getRight();
        Pizza pizza = (Pizza) model.get(DomainHelper.PIZZA);
        return populatePizzaList(workbookAndModel.getLeft(), Collections.singletonList(pizza));
    }

    static int createPizzaDataRow(Pizza pizza, Sheet sheet, int rowCount) {

        int colCount = 0;
        // Create data cells
        Row row = sheet.createRow(rowCount);
        String pizzaId = String.valueOf(pizza.getPizzaId());
        String price = String.valueOf(pizza.getPrice());
        createAndPopulateCell(row, colCount++, null, pizzaId);
        createAndPopulateCell(row, colCount++, null, pizza.getName());
        createAndPopulateCell(row, colCount++, null, pizza.getType().name());
        createAndPopulateCell(row, colCount, null, price);
        return colCount;
    }

}
