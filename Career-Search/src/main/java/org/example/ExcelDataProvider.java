package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;






import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataProvider{

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private String getHyperlink(Cell cell) {
        if (cell == null) return "";
        Hyperlink link = cell.getHyperlink();
        return (link != null) ? link.getAddress() : getCellValue(cell);
    }

    @DataProvider(name="data")
    public Object[][] readData() throws IOException {
        FileInputStream fis = new FileInputStream("src//main//resources//Companies.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        List<Object[]> data = new ArrayList<>();
        for(Row row: sheet){
            if (row.getRowNum()==2){
           Object[] rowData = new Object[]{

                   getCellValue(row.getCell(1)), getHyperlink(row.getCell(5))
           };
           data.add(rowData);
        }}
        workbook.close();
        fis.close();
        return data.toArray(new Object[0][]);
    }

}
