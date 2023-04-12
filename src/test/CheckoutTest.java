package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import main.pages.*;

public class CheckoutTest extends AbstractTest {
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void testCheckOutWithValidDate() {
        productPage.getProducts().get(3).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        checkoutPage.preCheckout("User", "Name", "00000");
        checkoutOverviewPage.finishCheckout();
        Assert.assertEquals("Checkout: Complete!", checkoutCompletePage.getTitleName());
        Assert.assertEquals("Back Home", checkoutCompletePage.getBackHomeBtnName());
        checkoutCompletePage.backHome();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testCheckOutWithInValidFirstName() {
        productPage.getProducts().get(3).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        checkoutPage.preCheckout("PPPPsdP", "Name", "00000");
        checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getTitleName(), "Error");
    }

    @Test
    public void testCheckOutWithInValidLastName() {
        productPage.getProducts().get(3).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.checkout();
        Assert.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
        checkoutPage.preCheckout("User", "AAAAAA", "00000");
        checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getTitleName(), "Error");
    }

    @Test
    public void testCheckOutWithInValidPostalCode() {
        productPage.getProducts().get(3).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        checkoutPage.preCheckout("User", "Name", "AAsc");
        checkoutOverviewPage.finishCheckout();
        Assert.assertEquals(checkoutCompletePage.getTitleName(), "Error");
    }

    @Test
    public void testCancellation() {
        productPage.getProducts().get(3).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        checkoutOverviewPage.cancellationCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getNameOfShoppingBtn(), "Continue Shopping");
        cartPage.continueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}



