package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.ArrayList;

public class ProductPage {
    WebDriver driver;

    By productNames = By.className("inventory_item_name");
    By sortDropdown = By.className("product_sort_container");
    By productPrices = By.className("inventory_item_price");
    By addToCartButtons = By.cssSelector(".btn_primary.btn_inventory");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Get all product names as a list
    public List<String> getProductNames() {
        List<WebElement> items = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement item : items) {
            names.add(item.getText());
        }
        return names;
    }

    // Sort products using the dropdown
    public void sortBy(String option) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(option);
    }

    // Get all prices as numbers
    public List<Double> getProductPrices() {
        List<WebElement> items = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();
        for (WebElement item : items) {
            String price = item.getText().replace("$", "");
            prices.add(Double.parseDouble(price));
        }
        return prices;
    }

    // Check how many products are shown
    public int getProductCount() {
        return driver.findElements(productNames).size();
    }
}