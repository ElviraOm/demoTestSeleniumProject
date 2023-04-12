package main.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HamburgerMenu {

    private final By openMenuBtn = By.id("react-burger-menu-btn");
    private final By menu = By.className("bm-item-list");
    private final By closeMenuBtn = By.id("react-burger-cross-btn");
    private final By logoutBtn = By.id("logout_sidebar_link");
    private final WebDriver driver;

    public HamburgerMenu(WebDriver driver) {
        this.driver = driver;
    }

    public void openMenu() {
        driver.findElement(openMenuBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(menu));
    }

    public boolean visibilityOfMenu() {
        return driver.findElement(menu).isDisplayed();
    }

    public void closeMenu() {
        driver.findElement(closeMenuBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(menu));
    }

    public void logoutFromMenu() {
        driver.findElement(logoutBtn).click();
    }
}
