package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void preCheckout(String firstNameIn, String lastNameIn, String postalCodeIn) {
        sendKeys(firstName, firstNameIn);
        sendKeys(lastName, lastNameIn);
        sendKeys(postalCode, postalCodeIn);
        click(continueBtn);
    }
}
