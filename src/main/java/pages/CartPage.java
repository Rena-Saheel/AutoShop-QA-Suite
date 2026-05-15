package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    By addToCartButton = By.cssSelector(".btn_primary.btn_inventory");
    By cartIcon = By.className("shopping_cart_link");
    By cartCount = By.className("shopping_cart_badge");
    By cartItems = By.className("cart_item");
    By removeButton = By.cssSelector("[data-test='remove-sauce-labs-backpack']");
    By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add first product to cart
    public void addFirstProductToCart() {
        driver.findElements(addToCartButton).get(0).click();
    }

    // Add multiple products to cart
    public void addMultipleProductsToCart(int count) {
        var buttons = driver.findElements(addToCartButton);
        for (int i = 0; i < count; i++) {
            buttons.get(i).click();
        }
    }

    // Get cart count number
    public int getCartCount() {
        return Integer.parseInt(driver.findElement(cartCount).getText());
    }

    // Go to cart page
    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    // Get number of items in cart
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    // Remove first item from cart
    public void removeFirstItem() {
        driver.findElement(removeButton).click();
    }

    // Check if cart is empty
    public boolean isCartEmpty() {
        return driver.findElements(cartCount).isEmpty();
    }
}