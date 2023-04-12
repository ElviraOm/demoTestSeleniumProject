package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductViewPage extends BasePage {

    private final By productDetail = By.className("inventory_details_desc");

    public ProductViewPage(WebDriver driver) {
        super(driver);
    }

    public boolean visibilityOfProductDetails() {
        return find(productDetail).isDisplayed();
    }
}
