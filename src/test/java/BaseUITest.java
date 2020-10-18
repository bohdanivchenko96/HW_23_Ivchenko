import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseUITest {

    public WebDriver driver;
    public WebDriverWait wait;
    @BeforeClass
    public void initialChromeDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments( "disable-extensions", "disable-popup-blocking", "start-maximized");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 20);


    }

    @AfterClass
    public void closeWebDriver() {
        driver.quit();
    }
}
