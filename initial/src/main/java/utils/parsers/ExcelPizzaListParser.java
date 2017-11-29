package utils.parsers;

import dto.PizzaDto;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelPizzaListParser implements CustomParser<PizzaDto> {

    public static final int MAX_ROWS_TO_SKIP_AT_THE_BEGINNING = 10;

    private final static List<String> PIZZALIST_COLUMN_NAMES = Arrays.asList("name", "type", "price");

    public static void main(String[] args) throws FileNotFoundException {
        File testFile = new File("E:\\IdeaProjects\\PizzaService2\\src\\main\\resources\\pizzaPrice.xlsx");
        List<PizzaDto> importPriceList =  new ExcelPizzaListParser().parse(new FileInputStream(testFile));
        System.out.println(importPriceList);
    }

    public List<PizzaDto> parse(InputStream file) {
        Workbook workbook = null;
        try {
            List<PizzaDto> pizzaDtos = new ArrayList<>();
            workbook = WorkbookFactory.create(file);

            Sheet sheet = workbook.getSheetAt(0);

            int totalRows = sheet.getPhysicalNumberOfRows();

            Map<String, Integer> map = new HashMap<>(); //Create map
            Row row = null;
            int i = 0;
            while ((row = sheet.getRow(i)) == null && i++ < MAX_ROWS_TO_SKIP_AT_THE_BEGINNING){ //Get first row
            }
            Assert.notNull(row, "Couldn't find the first roe in the file");

            //following is boilerplate from the java doc
            short minColIx = row.getFirstCellNum(); //get the first column index for a row
            short maxColIx = row.getLastCellNum(); //get the last column index for a row
            for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
                Cell cell = row.getCell(colIx); //get the cell
                map.put(cell.getStringCellValue(), cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
            }
            int idxForColumn1 = map.get(PIZZALIST_COLUMN_NAMES.get(0)); //get the column index for the column with header name = "Column1"
            int idxForColumn2 = map.get(PIZZALIST_COLUMN_NAMES.get(1)); //get the column index for the column with header name = "Column2"
            int idxForColumn3 = map.get(PIZZALIST_COLUMN_NAMES.get(2)); //get the column index for the column with header name = "Column3"

            for (int x = i + 1; x < i + totalRows; x++) {
                PizzaDto.PizzaDtoBuilder pizzaBuilder = PizzaDto.builder();
                Row dataRow = sheet.getRow(x); //get row 1 to row n (rows containing data)

                pizzaBuilder.name(dataRow.getCell(idxForColumn1).getStringCellValue()); //Get the values out of those cells and put them into the report row object
                pizzaBuilder.type(dataRow.getCell(idxForColumn2).getStringCellValue());
                pizzaBuilder.price(dataRow.getCell(idxForColumn3).getNumericCellValue());

                pizzaDtos.add(pizzaBuilder.build());
            }
            return pizzaDtos;
        }
        catch (Exception ioe) {
            throw new RuntimeException("Error while parsing file", ioe);
        }
    }

    private String getCellValue(Row row, int col) {
        Cell cell = row.getCell((short) col);

        if (cell != null) {
            // Your code here
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                default:
                    return "";
            }
        }
        return "";
    }

    private int getCols(Sheet sheet, int rows) {
        Row row;
        int tmp, cols = 0;
        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for (int i = 0; i < MAX_ROWS_TO_SKIP_AT_THE_BEGINNING || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                cols = tmp > cols ? tmp : cols;
            }
        }
        return cols;
    }
}
