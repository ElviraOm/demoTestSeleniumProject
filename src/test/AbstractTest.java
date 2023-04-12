package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import main.utils.IDriverAware;
import main.utils.TestListener;

@Listeners(TestListener.class)
public abstract class AbstractTest implements IDriverAware {
    protected WebDriver driver;

    public WebDriver getDriver()
    {
        return this.driver;
    }
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
}
