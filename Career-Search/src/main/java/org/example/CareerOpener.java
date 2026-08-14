package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;


public class CareerOpener{

    WebDriver driver;

    @FindBy(xpath = "//h2[text()='Experienced Professionals']")
    WebElement experiencedProfessionals;

    @FindBy(xpath="//a[text()='India']")
    WebElement india;

    @FindBy(css = "input[placeholder='Skill / Keyword']")
    WebElement searchBoxTxt;

    @FindBy(xpath = "//button[text()='Find Jobs']")
    WebElement findJobsBtn;

    @FindBy(xpath = "//div[contains(text(),'Work Experience of 4')]")
    WebElement workExperienceTxt;

    @FindBy(id="btn-search")
    WebElement searchBtn;

    @FindBy(css = ".sk-fading-circle.ng-star-inserted")
    WebElement loader;

    @FindBy(css="input[placeholder='Your Skills..']")
    WebElement skills;

    @FindBy(xpath = "//span[text()=' Technology->Automated Testing->Selenium-Java ']")
    WebElement skillOption;
    public CareerOpener(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void infosys(String url){
        Logger log =    LogManager.getLogger(String.valueOf(CareerOpener.class));
        Actions action= new Actions(driver);
        Helper helper = new Helper();
        driver.get(url);
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,500)");
        helper.clickElement(driver, experiencedProfessionals,"Experienced Professionals");

        helper.wait(driver,searchBtn,"Search Button");
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)");
        helper.clickElement(driver,india,"India");

        String parentWindow = driver.getWindowHandle();
        for(String childWindow : driver.getWindowHandles()){
            if(!parentWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
            }
        }
        helper.sendKeysToElement(driver,searchBoxTxt,"java selenium","Search Box");
        helper.clickElement(driver,findJobsBtn,"Find Jobs Button");

        action.pause(Duration.ofSeconds(5)).perform();
        helper.sendKeysToElement(driver,skills,"Selenium-Java","Skills");
        action.sendKeys(Keys.BACK_SPACE).perform();
        helper.clickElement(driver,skillOption,"Technology->Automated Testing->Selenium-Java");

        helper.clickElement(driver,workExperienceTxt,"Work Experience Text");
        if(workExperienceTxt.isDisplayed()){
            log.info("Available");
        }
    }

}
