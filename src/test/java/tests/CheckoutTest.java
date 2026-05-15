package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.LoginPage;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void completeCheckoutTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.addProductAndGoToCart();
        checkoutPage.clickCheckout();
        checkoutPage.fillCheckoutDetails("Rena", "Saheel", "670001");
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test
    public void checkoutWithEmptyFirstNameTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.addProductAndGoToCart();
        checkoutPage.clickCheckout();
        checkoutPage.fillCheckoutDetails("", "Saheel", "670001");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test
    public void checkoutWithEmptyLastNameTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.addProductAndGoToCart();
        checkoutPage.clickCheckout();
        // Type first name, skip last name
        checkoutPage.fillCheckoutDetailsStep("Rena", "", "670001");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"));
    }

    @Test
    public void checkoutWithEmptyPostalCodeTest() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.addProductAndGoToCart();
        checkoutPage.clickCheckout();
        // Type first and last name, skip postal code
        checkoutPage.fillCheckoutDetailsStep("Rena", "Saheel", "");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"));
    }
}