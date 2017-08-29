package tests.git;

import core.AbstractPage;
import org.openqa.selenium.By;
import tests.git.GitHubPage;

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
        browser.click(CREATE_NEW_BUTTON);
        fill(fileName, text, header, message);
        while (browser.isElementPresented(ERROR_LOCATOR)){
            Random random = new Random();
            fileName += (random.nextInt(101));
            fill(fileName, text, header, message);
        }
        return new GitHubPage();
    }

    public boolean isFileCreated(String fileName){
        return browser.isElementPresented(By.xpath(".//*[contains(text(), '" + fileName + "')]"));
    }

    private void fill(String fileName, String text, String header, String message) {
        browser.clear(NEW_FILE_NAME);
        browser.clickAndSendText(NEW_FILE_NAME, fileName);
        browser.clickAndSendText(TEXT_BOX, text);
        browser.clear(COMMIT_HEADER);
        browser.clickAndSendText(COMMIT_HEADER, header);
        browser.clear(COMMIT_MESSAGE);
        browser.clickAndSendText(COMMIT_MESSAGE, message);
        browser.click(SUBMIT_BUTTON);
    }


}
