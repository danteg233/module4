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
        return new GitHubPage();
    }

    public boolean isFileCreated(String fileName){
        return isElementPresented(By.xpath(".//*[contains(text(), '" + fileName + "')]"));
    }

    private void fill(String fileName, String text, String header, String message) {
        webDriver.findElement(NEW_FILE_NAME).clear();
        clickAndSendText(NEW_FILE_NAME, fileName);
        clickAndSendText(TEXT_BOX, text);
        webDriver.findElement(COMMIT_HEADER).clear();
        clickAndSendText(COMMIT_HEADER, header);
        webDriver.findElement(COMMIT_MESSAGE).clear();
        clickAndSendText(COMMIT_MESSAGE, message);
        click(SUBMIT_BUTTON);
    }


}
