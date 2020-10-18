import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.List;
import java.util.Set;

public class GuruCookiesTest extends BaseUITest{
    private String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @BeforeMethod
    public void getUrlPath() {
        driver.get(loginUrl);
    }

    @Test
    void GuruCookiesTest() {
        loginOnGuruSite();
        getAllCookies();
        driver.manage().deleteAllCookies();
        System.out.println("AFTER DELETING");
        getAllCookies();
        driver.navigate().refresh();
        checkAuthorization();
    }

    private void checkAuthorization() {
        WebElement logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Log out")));
        Assert.assertTrue(logOutButton.isDisplayed());
        System.out.println("Test is complete");
    }



    private void getAllCookies() {

        Set<Cookie> cookies = driver.manage().getCookies();

        for(Cookie cookie : cookies) {
            System.out.println(cookie.toJson());
        }
    }

    private void loginOnGuruSite() {
        driver.findElement(By.name("uid")).sendKeys("1303");
        driver.findElement(By.name("password")).sendKeys("Guru99");
        driver.findElement(By.name("btnLogin")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Log out")));
    }
}
