package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import main.pages.LoginPage;
import main.pages.Product;
import main.pages.ProductPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductTest extends AbstractTest {
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
    public void testSortByNameAZ() {
        productPage.setSortOrder("az");
        List<Product> elements = productPage.getProducts();
        List<String> productsAZ = new ArrayList<>(elements.size());
        for (Product el : elements) {
            productsAZ.add(el.getTitle());
        }
        List<String> expectedResult = new ArrayList<>(productsAZ);
        Collections.sort(expectedResult);

        Assert.assertEquals(productsAZ, expectedResult);
    }

    @Test
    public void testSortByNameZA() {
        productPage.setSortOrder("za");
        List<Product> elements = productPage.getProducts();
        List<String> productsZA = new ArrayList<>(elements.size());
        for (Product el : elements) {
            productsZA.add(el.getTitle());
        }
        List<String> expectedResult = new ArrayList<>(productsZA);
        Collections.sort(expectedResult);
        Collections.reverse(expectedResult);

        Assert.assertEquals(productsZA, expectedResult);
    }

    @Test
    public void testSortByPriceLowToHigh() {
        productPage.setSortOrder("lohi");
        List<Product> elements = productPage.getProducts();
        List<Float> productsLowToHigh = new ArrayList<>(elements.size());
        for (Product el : elements) {
            productsLowToHigh.add(el.getPrice());
        }
        List<Float> expectedResult = new ArrayList<>(productsLowToHigh);
        Collections.sort(expectedResult);

        Assert.assertEquals(productsLowToHigh, expectedResult);
    }

    @Test
    public void testSortByPriceHighToLow() {
        productPage.setSortOrder("hilo");
        List<Product> elements = productPage.getProducts();
        List<Float> productsHighToLow = new ArrayList<>(elements.size());
        for (Product el : elements) {
            productsHighToLow.add(el.getPrice());
        }
        List<Float> expectedResult = new ArrayList<>(productsHighToLow);
        Collections.sort(expectedResult);
        Collections.reverse(expectedResult);

        Assert.assertEquals(productsHighToLow, expectedResult);
    }

    @Test
    public void testAddingItemToCart() {
        List<Product> elements = productPage.getProducts();
        Product addingItem = elements.get(0);
        addingItem.inventoryBtnClick();
        String actualButton = addingItem.getButtonName();

        Assert.assertEquals("Remove", actualButton);
        Assert.assertEquals("1", productPage.getNumberInCartBadge());
    }

    @Test
    public void testRemovingItemFromCart() {
        //Add more than two items to cart
        List<Product> elements = productPage.getProducts();
        Product firstItem = elements.get(0);
        Product secondItem = elements.get(1);
        firstItem.inventoryBtnClick();
        secondItem.inventoryBtnClick();
        //Verify that items are selected successfully
        Assert.assertEquals("Remove", firstItem.getButtonName());
        Assert.assertEquals("Remove", secondItem.getButtonName());
        Assert.assertEquals("2", productPage.getNumberInCartBadge());
        //Remove one of selected items by clicking on the "remove" button on the item
        secondItem.inventoryBtnClick();
        //Verify  item is removed successfully and reflected as 'add to cart' instead of 'remove', with a decrement in the numbered marker on the top right corner of the 'cart' icon
        Assert.assertEquals("Add to cart", secondItem.getButtonName());
        Assert.assertEquals("1", productPage.getNumberInCartBadge());
    }
}


