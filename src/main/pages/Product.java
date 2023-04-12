package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {

    private final By titleOfProduct = By.className("inventory_item_name");
    private final By priseOfProduct = By.className("inventory_item_price");
    private final By inventoryBtn = By.className("btn_inventory");
    private final WebElement element;

    public Product(WebElement element) {
        this.element = element;
    }

    public String getTitle() {
        return element.findElement(titleOfProduct).getText();
    }

    public Float getPrice() {
        return Float.parseFloat(element.findElement(priseOfProduct).getText().replace("$", ""));
    }

    public void inventoryBtnClick() {
        element.findElement(inventoryBtn).click();
    }

    public String getButtonName() {
        return element.findElement(inventoryBtn).getText();
    }
}
