package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By title = By.className("title");
    private final By backHomeBtn = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getTitleName() {
        return getText(title);
    }

    public String getBackHomeBtnName() {
        return getText(backHomeBtn);
    }

    public void backHome() {
        click(backHomeBtn);
    }
}
