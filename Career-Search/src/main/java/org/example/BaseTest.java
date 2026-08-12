package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        driver = new ChromeDriver();
    }
    
    @Test(dataProvider = "data", dataProviderClass = ExcelDataProvider.class)
    public void companyTest(String company, String pageLink){
        CareerOpener career = new CareerOpener(driver);
        career.infosys(pageLink);
    }

    @AfterTest
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }


}
