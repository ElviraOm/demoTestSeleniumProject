package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {
    private final By finishBtn = By.id("finish");
    private final By cancelBtn = By.id("cancel");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void finishCheckout() {
        click(finishBtn);
    }

    public void cancellationCheckout() {
        click(cancelBtn);
    }
}
