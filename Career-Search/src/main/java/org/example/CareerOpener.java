package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public CareerOpener(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    public void infosys(String url){
        Logger log =    LogManager.getLogger(String.valueOf(CareerOpener.class));
        Helper helper = new Helper();
        driver.get(url);
        //wait.until(ExpectedConditions.elementToBeClickable(experiencedProfessionals));
        helper.wait(driver, experiencedProfessionals);
        helper.scrollToElement(driver, experiencedProfessionals);
        experiencedProfessionals.click();
        helper.wait(driver, india);
        helper.scrollToElement(driver, india);
        india.click();
        helper.wait(driver,searchBoxTxt);
        helper.scrollToElement(driver, india);
        searchBoxTxt.sendKeys("java selenium");
        helper.wait(driver,findJobsBtn);
        helper.scrollToElement(driver, india);
        findJobsBtn.click();
        helper.wait(driver,workExperienceTxt);
        helper.scrollToElement(driver, india);
        if(workExperienceTxt.isDisplayed()){
            log.info("Available");
        }
    }

}
