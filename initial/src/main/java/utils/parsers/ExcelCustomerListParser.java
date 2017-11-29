package utils.parsers;

import dto.CustomerDto;
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
public class ExcelCustomerListParser implements CustomParser<CustomerDto> {

    public static final int MAX_ROWS_TO_SKIP_AT_THE_BEGINNING = 10;

    private final static List<String> CUSTOMERLIST_COLUMN_NAMES = Arrays.asList("name", "email");

    public static void main(String[] args) throws FileNotFoundException {
        File testFile = new File("E:\\IdeaProjects\\PizzaService2\\src\\main\\resources\\customerList.xlsx");
        List<CustomerDto> importPriceList =  new ExcelCustomerListParser().parse(new FileInputStream(testFile));
        System.out.println(importPriceList);
    }

    public List<CustomerDto> parse(InputStream file) {
        Workbook workbook = null;
        try {
            List<CustomerDto> customerDtos = new ArrayList<>();
            workbook = WorkbookFactory.create(file);

            Sheet sheet = workbook.getSheetAt(0);

            int totalRows = sheet.getPhysicalNumberOfRows();

            Map<String, Integer> map = new HashMap<>(); //Create map
            Row row = null;
            int i = 0;
            while ((row = sheet.getRow(i)) == null && i++ < MAX_ROWS_TO_SKIP_AT_THE_BEGINNING){ //Get first row
            }
            Assert.notNull(row, "Couldn't find the first row in the file");

            //following is boilerplate from the java doc
            short minColIx = row.getFirstCellNum(); //get the first column index for a row
            short maxColIx = row.getLastCellNum(); //get the last column index for a row
            for (short colIx = minColIx; colIx < maxColIx; colIx++) { //loop from first to last index
                Cell cell = row.getCell(colIx); //get the cell
                map.put(cell.getStringCellValue(), cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
            }
            int idxForColumn1 = map.get(CUSTOMERLIST_COLUMN_NAMES.get(0)); //get the column index for the column with header name = "Column1"
            int idxForColumn2 = map.get(CUSTOMERLIST_COLUMN_NAMES.get(1)); //get the column index for the column with header name = "Column2"

            for (int x = i + 1; x < i + totalRows; x++) {
                CustomerDto.CustomerDtoBuilder customerDtoBuilder = CustomerDto.builder();
                Row dataRow = sheet.getRow(x); //get row 1 to row n (rows containing data)

                customerDtoBuilder.name(dataRow.getCell(idxForColumn1).getStringCellValue()); //Get the values out of those cells and put them into the report row object
                customerDtoBuilder.email(dataRow.getCell(idxForColumn2).getStringCellValue());

                customerDtos.add(customerDtoBuilder.build());
            }
            return customerDtos;
        }
        catch (Exception ioe) {
            throw new RuntimeException("Error while parsing file", ioe);
        }
    }

}
