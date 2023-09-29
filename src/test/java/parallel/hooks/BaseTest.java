package parallel.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {

    protected static WebDriver driver;

    @Before
    public void init(){
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    public static WebDriver getDriver(){
        return driver;
    }

    @After
    public void close(){
        driver.close();
    }

}
