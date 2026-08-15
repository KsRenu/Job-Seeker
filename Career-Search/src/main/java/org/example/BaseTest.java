package org.example;

import jakarta.mail.MessagingException;
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
        driver.manage().window().maximize();
    }
    
    @Test(dataProvider = "data", dataProviderClass = ExcelDataProvider.class)
    public void companyTest(String company, String pageLink) throws MessagingException {
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
