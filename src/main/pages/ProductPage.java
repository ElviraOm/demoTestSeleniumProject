package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {

    public final HamburgerMenu hamburgerMenu;
    private final By sortContainer = By.className("product_sort_container");
    private final By product = By.className("inventory_item");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By allProductSet = By.id("inventory_container");

    public ProductPage(WebDriver driver) {
        super(driver);
        hamburgerMenu = new HamburgerMenu(driver);
    }

    public void setSortOrder(String sortOrder) {
        Select sort = new Select(find(sortContainer));
        sort.selectByValue(sortOrder);
    }

    public List<Product> getProducts() {
        List<WebElement> list = driver.findElements(product);
        List<Product> productList = new ArrayList<>(list.size());
        for (WebElement el : list) {
            Product p = new Product(el);
            productList.add(p);
        }

        return productList;
    }

    public void toCartBadge() {
        click(cartBadge);
    }

    public String getNumberInCartBadge() {
        return getText(cartBadge);
    }

    public boolean visibilityOfAllProductSet() {
        return find(allProductSet).isDisplayed();
    }
}
