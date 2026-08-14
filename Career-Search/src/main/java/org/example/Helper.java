package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class Helper {

    public final Logger log = Logger.getLogger(Helper.class.getName());


    public void wait(WebDriver driver, WebElement element, String elementName){
        By loaderPesent= By.cssSelector(".sk-fading-circle.ng-star-inserted");
        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(30));
        if(driver.findElements(loaderPesent).size()>0){
            log.info("Waiting for loader to disappear before interacting with element: " + elementName);
            waits.until(ExpectedConditions.invisibilityOfElementLocated(loaderPesent));
            log.info("Loader disappeared. Now waiting for element: " + elementName);
            waits.until(ExpectedConditions.visibilityOf(element));
            waits.until(ExpectedConditions.elementToBeClickable(element));
        }
        else{
            log.info("No loader present. Waiting for element: " + elementName);
        waits.until(ExpectedConditions.visibilityOf(element));
        waits.until(ExpectedConditions.elementToBeClickable(element));}

    }

    public void scrollToElement(WebDriver driver, WebElement element, String elementName){
       // JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("arguments[0].scrollIntoView()",element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        log.info("Scrolled to element: " + elementName);
    }

    public void clickElement(WebDriver driver, WebElement element, String elementName){
        wait(driver, element, elementName);
        scrollToElement(driver, element, elementName);
        element.click();
        log.info("Clicked on element: " + elementName);
    }


    public void clearElement(WebDriver driver, WebElement element, String elementName){
        wait(driver, element, elementName);
        scrollToElement(driver, element, elementName);
        element.clear();
        log.info("Cleared the element: " + elementName);
    }

    public void sendKeysToElement(WebDriver driver, WebElement element, String keys, String elementName){
        Actions action= new Actions(driver);
        wait(driver, element, elementName);
        scrollToElement(driver, element,    elementName);
        clearElement(driver, element, elementName);
        //element.sendKeys(keys);
        for (char c : keys.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            action.pause(Duration.ofMillis(100)).perform();
        }
        log.info("filled the element: " +elementName + " | Keys: " + keys);
    }

    public void selectFromDropdown(WebDriver driver, WebElement dropdown, String value, String elementName){
        wait(driver, dropdown, elementName);
        scrollToElement(driver, dropdown, elementName);
        dropdown.click();
        WebElement option = driver.findElement(By.xpath("//option[text()='" + value + "']"));
        wait(driver, option, "Option: " + value);
        //scrollToElement(driver, option);
        option.click();
        log.info("Selected from dropdown: " + elementName + " | Value: " + value);
    }
}
