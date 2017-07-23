package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ketul on 7/20/2017.
 */
public class ItemDetailPage extends BasePage {

    private WebDriver driver;
    BasePage basePage = new BasePage(driver);

    //Locator
    private By addToCartButton = By.xpath("//button[text()='ADD TO CART']");
    private By itemName = By.xpath("//h1[contains(.,'iPhone 7')]");

    public ItemDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        basePage.waitForPageToLoad(driver);
    }

    /*
    Verify Add to Cart button is present
    */
    public boolean isAddToCartButtonPresent(){
        return basePage.isElementPresent(driver,addToCartButton);
    }

    /*
    Verify correct item is opened
    */
    public boolean isItemDetailCorrect(){
        return basePage.isElementPresent(driver, itemName);
    }

    /*
    Click Add to Cart button
    */
    public CartPage clickAddtoCartButton(){
        basePage.waitAndClick(driver, addToCartButton);
        return new CartPage(driver);
    }
}
