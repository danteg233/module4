package tests.git;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Random;

public class NewFilePage extends AbstractPage {

    private static final By CREATE_NEW_BUTTON = By.xpath(".//button[@class='btn btn-sm BtnGroup-item']");
    private static final By NEW_FILE_NAME = By.xpath(".//*[@name='filename']");
    private static final By TEXT_BOX = By.xpath(".//*[@class='CodeMirror-sizer']");
    private static final By COMMIT_HEADER = By.xpath(".//*[@name='message']");
    private static final By COMMIT_MESSAGE = By.xpath(".//*[@id='commit-description-textarea']");
    private static final By SUBMIT_BUTTON = By.xpath(".//*[@id='submit-file']");
    private static final By ERROR_LOCATOR = By.xpath(".//*[@id='js-flash-container']/div");


    public GitHubPage createNewFile(String fileName, String text, String header, String message){
        webDriver.findElement(CREATE_NEW_BUTTON).click();
        fill(fileName, text, header, message);
        while (isElementPresented(ERROR_LOCATOR)){
            Random random = new Random();
            fileName += (random.nextInt(101));
            fill(fileName, text, header, message);
        }
        Assert.assertTrue(isElementPresented(By.xpath(".//*[contains(text(), '" + fileName + "')]")));
        return new GitHubPage();
    }

    private void fill(String fileName, String text, String header, String message) {
        webDriver.findElement(NEW_FILE_NAME).clear();
        webDriver.findElement(NEW_FILE_NAME).click();
        webDriver.findElement(NEW_FILE_NAME).sendKeys(fileName);
        webDriver.findElement(TEXT_BOX).click();
        webDriver.findElement(TEXT_BOX).sendKeys(text);
        webDriver.findElement(COMMIT_HEADER).clear();
        webDriver.findElement(COMMIT_HEADER).click();
        webDriver.findElement(COMMIT_HEADER).sendKeys(header);
        webDriver.findElement(COMMIT_MESSAGE).clear();
        webDriver.findElement(COMMIT_MESSAGE).click();
        webDriver.findElement(COMMIT_MESSAGE).sendKeys(message);
        webDriver.findElement(SUBMIT_BUTTON).click();
    }


}
