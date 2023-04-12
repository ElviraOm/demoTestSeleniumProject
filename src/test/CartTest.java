package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import main.pages.*;
import java.util.List;

public class CartTest extends AbstractTest {
    CartPage cartPage;
    ProductPage productPage;
    ProductViewPage productViewPage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        productPage = new ProductPage(driver);
        productViewPage = new ProductViewPage(driver);
        loginPage.loginUser("standard_user", "secret_sauce");
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void testProductDetailsAreDisplayed() {
        productPage.getProducts().get(0).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.getItemsFromCart().get(0).toItemDetail();
        Assert.assertTrue(productViewPage.visibilityOfProductDetails());
    }

    @Test
    public void testRemovingItemFromCartPage() {
        productPage.getProducts().get(1).inventoryBtnClick();
        productPage.getProducts().get(0).inventoryBtnClick();
        productPage.toCartBadge();
//        Verify count of chosen items
        List<CartItem> elements = cartPage.getItemsFromCart();
        Assert.assertEquals(2, elements.size());
//        Verify that names of element are correct
        Assert.assertEquals("Sauce Labs Bike Light", elements.get(0).getNameOfItemTitle());
        Assert.assertEquals("Sauce Labs Backpack", elements.get(1).getNameOfItemTitle());
//        Verify removing item
        cartPage.getItemsFromCart().get(1).removingItemFromCart();
        List<CartItem> elementsAfterRemoving = cartPage.getItemsFromCart();
        Assert.assertEquals(1, elementsAfterRemoving.size());
        Assert.assertEquals("Sauce Labs Bike Light", elementsAfterRemoving.get(0).getNameOfItemTitle());
        Assert.assertEquals("1", productPage.getNumberInCartBadge());
    }

    @Test
    public void testAddItemAfterContinuingShopping() {
        productPage.getProducts().get(0).inventoryBtnClick();
        productPage.toCartBadge();
        cartPage.continueShopping();
        productPage.getProducts().get(2).inventoryBtnClick();
        productPage.toCartBadge();
        List<CartItem> elements = cartPage.getItemsFromCart();

        Assert.assertEquals(2, elements.size());
        Assert.assertEquals("Sauce Labs Backpack", elements.get(0).getNameOfItemTitle());
        Assert.assertEquals("Sauce Labs Bolt T-Shirt", elements.get(1).getNameOfItemTitle());
    }
}


