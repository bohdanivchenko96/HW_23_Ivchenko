import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class GmailTest extends BaseUITest{
    String loginUrl = "https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
    String ownEMAIL = "";//enter your gmail login
    String ownPassword = "";//enter your gmail password
    String textToSend = "it is my text";
    String yourFilePath = "";//create file and enter the ful path
    String fileName = ""; //enter the full file name



    @Test
    public void gmailTestExercise() throws Exception {
        goToStackOverflow();// if You have not issue with login gmail immediately You can to comment this method
        signInGMail();
        openFormForCreateLetter();
        switchToFormForCreateLetter();
        uploadFile();
        sentLetter();
        goToInbox();
        checkOwnSubjectOfLetterInLetterbox();
        goToInbox();
        checkOwnSubjectOfLetterInLetterbox();
        verifyOurLetter();
    }

    private void verifyOurLetter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='main']")));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("go")));
        String mailWithTagSymbol = driver.findElement(By.className("go")).getText();
        String mailWithoutTagSymbol = mailWithTagSymbol.substring(1, (mailWithTagSymbol.length() - 1));
        Assert.assertEquals(mailWithoutTagSymbol, ownEMAIL);
        System.out.println("Email is verified");
        WebElement contentText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='a3s aiL ']")));
        Assert.assertEquals(contentText.getText(), textToSend);
        System.out.println("Text content is verified");
        WebElement pinnedFile = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='aV3 zzV0ie']")));
        Assert.assertEquals(pinnedFile.getText(), fileName);
        System.out.println("Pinned file is verified");
    }


    private void checkOwnSubjectOfLetterInLetterbox() {
        driver.switchTo().defaultContent();
        int count = 0;
        wait.until(ExpectedConditions.elementToBeClickable(By.className("bog")));
        List<WebElement> subjectsList = driver.findElements(By.className("bog"));
        for(WebElement webElement : subjectsList) {
            String subjectName = webElement.getText();
            if(subjectName.equals("TestSubject") && count <= 2) {
                count++;
                webElement.click();
                break;
            } else {
                System.out.println("do not click on the letter");
            }
        }
    }

    private void goToInbox() {

        WebElement inboxButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class='J-Ke n0']")));
        inboxButton.click();
    }

    private void sentLetter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(":pz"))).click();
    }

    private void uploadFile() throws Exception {
        Robot robot = new Robot();
        robot.setAutoDelay(5000);

        StringSelection stringSelection = new StringSelection(yourFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.setAutoDelay(2000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(2000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);



    }

    private void switchToFormForCreateLetter() {
        WebElement createLetterForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea[name='to']")));
        createLetterForm.sendKeys(ownEMAIL);
        createLetterForm.sendKeys(Keys.TAB);
        WebElement subjectBoxField = driver.findElement(By.cssSelector("input[name='subjectbox']"));
        subjectBoxField.sendKeys("TestSubject");
        WebElement textField = driver.findElement(By.cssSelector("div[role='textbox']"));
        textField.sendKeys(textToSend);
        WebElement pinFilesIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[command='Files'")));
        pinFilesIcon.click();

    }

    private void openFormForCreateLetter() {
        WebElement composeLetter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='T-I T-I-KE L3']")));
        composeLetter.click();


    }

    private void goToStackOverflow() {
        driver.get("https://stackoverflow.com");
        driver.findElement(By.cssSelector("a[data-gps-track='login.click']")).click();
        WebElement signWithGoogle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-provider='google'")));
        signWithGoogle.click();
    }

    private void signInGMail() {
        driver.findElement(By.id("identifierId")).sendKeys(ownEMAIL);
        driver.findElement(By.className("VfPpkd-RLmnJb")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        driver.findElement(By.name("password")).sendKeys(ownPassword);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("VfPpkd-RLmnJb")));
        driver.findElement(By.className("VfPpkd-RLmnJb")).click();
        driver.get("https://mail.google.com/mail/u/0/?tab=km#inbox");
        driver.get(loginUrl);

    }
}
