package Tests;

import PageObjects.CartPage;
import PageObjects.HomePage;
import PageObjects.ItemDetailPage;
import PageObjects.SearchResultPage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ketul on 7/20/2017.
 */
@Test
public class MainTest extends TestBase{

    WebDriver driver;
    HomePage homePage;
    SearchResultPage searchResultPage;
    ItemDetailPage itemDetailPage;
    CartPage cartPage;
    JSONParser parser = new JSONParser();
    Object obj;
    JSONObject jsonObject;

    @BeforeClass
    public void setUp() throws IOException, ParseException {
        driver = newDriver();
        homePage = new HomePage(driver);
        obj =  parser.parse(new FileReader("config/testdate.json"));
        jsonObject = (JSONObject) obj;
    }

    /*Test log into flipkart account*/
    @Test(priority = 0)
    public void testLogin(){
        Assert.assertTrue(homePage.isLoginLinkPresent(),"Login link is not present");
        homePage.clickLogInLink();
        Assert.assertTrue(homePage.isUsernameTextboxPresnet(),"Username textbox is not present");
        Assert.assertTrue(homePage.isPasswordTextboxPresnet(), "Password textbox is not present");
        Assert.assertTrue(homePage.isSubmitButtonPresnet(), "Submit button is not present");

        homePage.enterLogInCred((String) jsonObject.get("username"), (String) jsonObject.get("password"));
        Assert.assertTrue(homePage.isAccountLinkPresent(), "Login was not successful");
    }

    /*Test search for item*/
    @Test(priority = 1)
    public void testSearchForItem(){
        Assert.assertTrue(homePage.isSearchTextboxPresnet(),"Search textbox is not present");
        Assert.assertTrue(homePage.isSearchButtonPresnet(), "Search button is not present");
        searchResultPage = homePage.searchForItem((String) jsonObject.get("item"));
        Assert.assertTrue(searchResultPage.isItemPresent(),"item is not present in search result page");
    }

    /*Test opening item details page from search result page*/
    @Test(priority = 2)
    public void testOpenItemDetailPage(){
        itemDetailPage = searchResultPage.clickItem();
        Assert.assertTrue(itemDetailPage.isItemDetailCorrect(),"Correct item is not opened");
        Assert.assertTrue(itemDetailPage.isAddToCartButtonPresent(),"Add to cart button is not present");
    }

    /*Test adding item to cart*/
    @Test(priority = 3)
    public void testAddItemToCart(){
        cartPage = itemDetailPage.clickAddtoCartButton();
        Assert.assertTrue(cartPage.isItemPresentInCart(),"Item is not present in cart");
    }

    /*Test logging out from flipkart account*/
    @Test(priority = 4)
    public void testLogout(){
        homePage.logOut();
        Assert.assertTrue(homePage.isLoginLinkPresent(),"Log in link is not present");
    }
}
