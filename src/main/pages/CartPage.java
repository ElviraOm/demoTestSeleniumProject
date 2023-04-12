package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By cartItem = By.className("cart_item");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItem> getItemsFromCart() {
        List<WebElement> list = driver.findElements(cartItem);
        List<CartItem> cartItems = new ArrayList<>(list.size());
        for (WebElement el : list) {
            CartItem p = new CartItem(el);
            cartItems.add(p);
        }
        return cartItems;
    }

    public void continueShopping() {
        click(continueShoppingBtn);
    }

    public void checkout() {
        click(checkoutBtn);
    }

    public String getNameOfShoppingBtn() {
        return getText(continueShoppingBtn);
    }
}
