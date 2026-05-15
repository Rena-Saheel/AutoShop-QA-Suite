package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import java.util.List;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void verifyProductCountTest() {
        ProductPage productPage = new ProductPage(driver);
        int count = productPage.getProductCount();
        Assert.assertEquals(count, 6, "There should be 6 products!");
    }

    @Test
    public void sortByNameAtoZTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.sortBy("Name (A to Z)");
        List<String> names = productPage.getProductNames();
        Assert.assertEquals(names.get(0), "Sauce Labs Backpack", "First product should be Backpack!");
    }

    @Test
    public void sortByNameZtoATest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.sortBy("Name (Z to A)");
        List<String> names = productPage.getProductNames();
        Assert.assertEquals(names.get(0), "Test.allTheThings() T-Shirt (Red)", "First product should be T-Shirt!");
    }

    @Test
    public void sortByPriceLowToHighTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.sortBy("Price (low to high)");
        List<Double> prices = productPage.getProductPrices();
        Assert.assertTrue(prices.get(0) < prices.get(prices.size() - 1), "Prices should go low to high!");
    }

    @Test
    public void sortByPriceHighToLowTest() {
        ProductPage productPage = new ProductPage(driver);
        productPage.sortBy("Price (high to low)");
        List<Double> prices = productPage.getProductPrices();
        Assert.assertTrue(prices.get(0) > prices.get(prices.size() - 1), "Prices should go high to low!");
    }
}