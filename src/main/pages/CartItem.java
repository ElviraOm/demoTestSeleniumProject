package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItem {
    private final By itemTitle = By.className("inventory_item_name");
    private final By removingFromCartBtn = By.className("cart_button");
    public WebElement element;

    public CartItem(WebElement element) {
        this.element = element;
    }

    public String getNameOfItemTitle() {
        return element.findElement(itemTitle).getText();
    }

    public void removingItemFromCart() {
        element.findElement(removingFromCartBtn).click();
    }

    public void toItemDetail() {
        element.findElement(itemTitle).click();
    }
}
