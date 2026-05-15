package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void addSingleProductToCartTest() {
        CartPage cartPage = new CartPage(driver);
        cartPage.addFirstProductToCart();
        Assert.assertEquals(cartPage.getCartCount(), 1, "Cart should have 1 item!");
    }

    @Test
    public void addMultipleProductsToCartTest() {
        CartPage cartPage = new CartPage(driver);
        cartPage.addMultipleProductsToCart(3);
        Assert.assertEquals(cartPage.getCartCount(), 3, "Cart should have 3 items!");
    }

    @Test
    public void removeProductFromCartTest() {
        CartPage cartPage = new CartPage(driver);
        cartPage.addFirstProductToCart();
        cartPage.goToCart();
        cartPage.removeFirstItem();
        Assert.assertTrue(cartPage.isCartEmpty(), "Cart should be empty after removing!");
    }

    @Test
    public void verifyCartItemCountTest() {
        CartPage cartPage = new CartPage(driver);
        cartPage.addMultipleProductsToCart(2);
        cartPage.goToCart();
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart page should show 2 items!");
    }
}