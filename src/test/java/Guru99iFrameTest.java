import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Guru99iFrameTest extends BaseUITest{
    private String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @BeforeMethod
    public void getUrlPath() {
        driver.get(loginUrl);
    }
    @Test
    void iFrameTest() throws Exception {
        WebElement playButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("playBtn")));
        playButton.click();
        System.out.println("Video is played");
        Thread.sleep(1500);
        cursorNavigating(playButton);


    }

    private void cursorNavigating(WebElement playButton) {
        int count = 1;
        while(true) {

            Actions action = new Actions(driver);
            WebElement loginButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Insurance Project")));
            action.moveToElement(loginButton).pause(1000).build().perform();
            WebElement iFrameElement = driver.findElement(By.id("transparentInner"));
            action.moveToElement(iFrameElement).pause(1000).build().perform();
            count++;
            if (driver.findElement(By.id("pauseBtn")).isDisplayed() && count > 5) {
                break;
            }
        }
    }
}
