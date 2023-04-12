package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By userName = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By error = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginUser(String login, String password) {
        sendKeys(userName, login);
        sendKeys(passwordInput, password);
        click(loginButton);
    }

    public String getErrorMessage() {
        return getText(error);
    }
}
