package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import main.pages.LoginPage;
import main.pages.ProductPage;

public class HamburgerMenuTest extends AbstractTest {
    ProductPage productPage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void testOpenAndCloseHamburgerMenu() {
        productPage.hamburgerMenu.openMenu();
        JavascriptExecutor scroll = (JavascriptExecutor) driver;
        scroll.executeScript("window.scrollBy(0,500)", "");
        scroll.executeScript("window.scrollBy(0,-500)", "");
//       Verify that the "hamburger" menu is listed successfully when right side of the page is scrolled.
        Assertions.assertTrue(productPage.hamburgerMenu.visibilityOfMenu());

        productPage.hamburgerMenu.closeMenu();
//        Verify that the "hamburger" menu is closed successfully and "Product" page is visible
        Assertions.assertFalse(productPage.hamburgerMenu.visibilityOfMenu());
        Assertions.assertTrue(productPage.visibilityOfAllProductSet());
    }

    @Test
    public void testClickableHamburgerMenu() {
        productPage.hamburgerMenu.openMenu();
        productPage.hamburgerMenu.logoutFromMenu();
        Assertions.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }

}


