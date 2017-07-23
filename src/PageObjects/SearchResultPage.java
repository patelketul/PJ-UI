package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ketul on 7/20/2017.
 */
public class SearchResultPage extends BasePage{

    private WebDriver driver;
    BasePage basePage = new BasePage(driver);

    //Locator
    private By item = By.cssSelector("a[href*='iphone-7'][target='_blank']");

    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        basePage.waitForPageToLoad(driver);
    }

    /*
    Verify searched item is present in search result or not
    */
    public boolean isItemPresent(){
        return basePage.isElementPresent(driver, item);
    }

    /*
    Click on item from search result page to view details of item
    */
    public ItemDetailPage clickItem(){
        basePage.waitAndClick(driver, item);
        basePage.switchToNewTab(driver);
        return new ItemDetailPage(driver);
    }
}
