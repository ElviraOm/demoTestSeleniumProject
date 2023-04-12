package test;

import org.testng.annotations.*;
import org.testng.Assert;
import main.pages.LoginPage;

public class LoginTest extends AbstractTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void quit() {
        driver.quit();
    }

    @Test
    public void testLoginWithValidData() {
        loginPage.loginUser("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testLoginWithInValidUserName() {
        loginPage.loginUser("000000", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testLoginWithInValidPassword() {
        loginPage.loginUser("standard_user", "000000");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }

}
