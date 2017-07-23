package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by New User on 20-Jul-17.
 */
public class CartPage extends BasePage {

    private WebDriver driver;
    BasePage basePage = new BasePage(driver);;

    //Locator
    private By item = By.partialLinkText("iPhone 7");

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        basePage.waitForPageToLoad(driver);
    }

    /*
    Verify item is present in the cart or not
    */
    public boolean isItemPresentInCart(){
        return basePage.isElementPresent(driver, item);
    }


}
