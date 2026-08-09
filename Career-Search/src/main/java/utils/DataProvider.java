package utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class DataProvider {
    @DataProvider(name="data")
    public Object[][] readData() {
        FileInputStream fis = new FileInputStream("Companies.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        for(Row row: sheet){
            System.out.println(row);
        }
    }

}
