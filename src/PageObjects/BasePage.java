package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

/**
 * Created by Ketul on 7/20/2017.
 */
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /*
    Wait for page to load completely
    */
    public void waitForPageToLoad(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,45);
        wait.until((com.google.common.base.Predicate<WebDriver>) driver1 -> {
            return ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        });
    }

    /*
    wait for element to be displayed on screen
    Parameter: locator of element
    */
    public void waitForElement(WebDriver driver, By Element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Element));
    }

    /*
   wait for element to be removed from screen
   Parameter: locator of element
   */
    public void waitForElementtoDisappear(WebDriver driver, By Element){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Element));
    }

    /*
    Wait for element and click
    parameter: locator of element
    */
    public void waitAndClick(WebDriver driver, By Element){
        waitForElement(driver, Element);
        driver.findElement(Element).click();
    }

    /*
    Verify element is displayed on the screen or not
    */
    public boolean isElementDisplayed(WebDriver driver,By Element){
        return driver.findElement(Element).isDisplayed();
    }

    /*
    Wait for element and enter text
    parameter:
    1. locator of element
    2. input string
     */
    public void waitAndType(WebDriver driver, By Element, String input){
        waitForElement(driver, Element);
        driver.findElement(Element).clear();
        driver.findElement(Element).sendKeys(input);
    }

    /*
    Verify element is present on the page
    parameters: locator of element
     */
    public boolean isElementPresent(WebDriver driver, By Element){
        try{
            waitForElement(driver, Element);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    /*
    When new window is opened, switch focus on new window
     */
    public void switchToNewTab(WebDriver driver){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        if(tabs.size()!=1){
            driver.switchTo().window(tabs.get(1));
        }
    }

    /*
    Switch back to old tab
    */
    public void switchToOldTab(){
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
    }

    /*
    Hover over element
    */
    public void hoverOverElement(WebDriver driver,By Element){
        Actions action  = new Actions(driver);
        action.moveToElement(driver.findElement(Element)).build().perform();
    }
}
