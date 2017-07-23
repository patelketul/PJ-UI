package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Ketul on 7/20/2017.
 */
public class HomePage extends BasePage {

    private WebDriver driver;

    //Locators
    private By logInLink = By.linkText("Log In");
    private By searchTextBox = By.cssSelector("input[title='Search for Products, Brands and More']");
    private By searchButton = By.cssSelector("button[type='Submit']");
    private By accountLink = By.cssSelector("a[href*=home_account]");
    private By logoutLink = By.linkText("Log Out");

    //Login screen
    private By usernameTextbox = By.cssSelector("form[autocomplete=on] input[type=text]");
    private By passwordTextbox = By.cssSelector("input[type=password]");
    private By submitButton = By.cssSelector("form[autocomplete=on] button[type=submit]");
    private By closeButton = By.cssSelector("button._2AkmmA._29YdH8");
    BasePage basePage = new BasePage(driver);


    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        basePage.waitForPageToLoad(driver);
    }

    /*
    Verify Log In link is present
    */
    public boolean isLoginLinkPresent(){
        return basePage.isElementPresent(driver, logInLink);
    }

    /*
    Verify Account Link is displayed or not
    */
    public boolean isAccoutLinkDisplayed(){
        return basePage.isElementDisplayed(driver, accountLink);
    }


    /*
    CLick Log in link on home page
     */
    public void clickLogInLink(){
        //Sometimes on opening flipkart page, log in pop-up is displayed.
        //To avoid this issue, below try-catch block will check for opened log in pop-up and will close it if opened
        try{
            basePage.waitAndClick(driver, closeButton);
        }
        catch (Exception e){

        }
        basePage.waitAndClick(driver, logInLink);
        basePage.waitForElement(driver, usernameTextbox);
    }

    /*
    Verify Search item textbox is present
     */
    public boolean isSearchTextboxPresnet(){
        return basePage.isElementPresent(driver, searchTextBox);
    }

    /*
    Verify Search item button is present
     */
    public boolean isSearchButtonPresnet(){
        return basePage.isElementPresent(driver, searchButton);
    }

    /*
    Verify Username textbox is present
     */
    public boolean isUsernameTextboxPresnet(){
        return basePage.isElementPresent(driver,usernameTextbox);
    }

    /*
    Verify password textbox is present
     */
    public boolean isPasswordTextboxPresnet(){
        return basePage.isElementPresent(driver, passwordTextbox);
    }

    /*
    Verify Submit button is present
    */
    public boolean isSubmitButtonPresnet(){
        return basePage.isElementPresent(driver,submitButton);
    }

    /*
    Enter log in credentials
    parameters:
    1. username
    2. password
     */
    public void enterLogInCred(String username, String password){
        basePage.waitAndType(driver,usernameTextbox, username);
        basePage.waitAndType(driver, passwordTextbox,password);
        basePage.waitAndClick(driver, submitButton);
    }

    /*
    Verify Account link is present when user logged in to account successfully
     */
    public boolean isAccountLinkPresent(){
        return basePage.isElementPresent(driver, accountLink);
    }

    /*
    Search for item
    parameter: Item name to be searched
     */
    public SearchResultPage searchForItem(String itemname){
        basePage.waitAndType(driver, searchTextBox, itemname);
        basePage.waitAndClick(driver,searchButton);
        return new SearchResultPage(driver);
    }

    /*
    Log out from account
    */
    public void logOut(){
        basePage.hoverOverElement(driver,accountLink);
        basePage.waitAndClick(driver, logoutLink);
        basePage.waitForElementtoDisappear(driver,accountLink);
    }
}
