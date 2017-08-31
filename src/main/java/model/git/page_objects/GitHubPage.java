
package model.git.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import reporting.MyLogger;


public class GitHubPage extends AbstractPage {
    private static final By NEW_REPOSITORY_BUTTON = By.xpath(".//*[@class='btn btn-sm btn-primary']");
    private static final By REPOSITORY_NAME = By.id("repository_name");
    private static final By INIT_WITH_README_LOCATOR = By.xpath(".//*[@id='repository_auto_init']");
    private static final By CREATE_REPOSITORY_BUTTON = By.xpath(".//*[@class='btn btn-primary first-in-line']");
    private static final By ERROR_MESSAGE = By.xpath(".//*[@class='error']");
    private static final By SETTING_BUTTON = By.xpath(".//*[@class='octicon octicon-gear']");
    private static final By DELETE_BUTTON = By.xpath(".//button[contains(text(), 'Delete this repository')]");
    private static final By DELETE_MENU = By.xpath(".//*[@class='facebox-popup']");
    private static final By CONFIRM_DELETE_TEXT = By.xpath(".//*[@class='facebox-popup']//input[@class='form-control input-block']");
    private static final By CONFIRM_DELETE_BUTTON = By.xpath(".//*[@id='facebox']//button[@class='btn btn-block btn-danger']");
    private static final By PROFILE_BUTTON = By.xpath(".//*[@data-ga-click='Header, show menu, icon:avatar']");
    private static final By PROFILE_MENU = By.xpath(".//*[@class='logout-form']");
    private static final By CONFIRM_LOG_OUT_LOCATOR = By.xpath("//*[@class='home-hero-signup js-signup-form']");

    public GitHubPage createRepository(String name) throws Exception {
        By temp = By.xpath(".//span[contains(text(), '" + name +"'" + ")]");
        if (browser.isElementPresented(temp)){
            browser.click(temp);
            return this;
        }
        browser.click(NEW_REPOSITORY_BUTTON);
        browser.clickAndSendText(REPOSITORY_NAME, name);
        browser.click(INIT_WITH_README_LOCATOR);
        if (browser.isElementPresented(ERROR_MESSAGE)){
            MyLogger.warn("Repository with this name already exist");
            throw new Exception("Repository with this name already exist");
        }
        browser.click(CREATE_REPOSITORY_BUTTON);
        return this;
    }

    public boolean isRepositoryCreated(String name){
        return browser.isElementPresented(By.xpath(".//*[contains(a, '" + name + "')]"));
    }

    public boolean isRepositoryDeleted(String name){
        return browser.isElementPresented(By.xpath(".//span[contains(text(), '" + name +"')]"));
    }

    public boolean isLogOutConfirmed(){
        return browser.isElementPresented(CONFIRM_LOG_OUT_LOCATOR);
    }



    public GitHubPage deleteRepository(String repositoryName){
        browser.click(SETTING_BUTTON);
        browser.scrollToElement(DELETE_BUTTON);
        browser.click(DELETE_BUTTON);
        browser.waitForElementEnabled(DELETE_MENU);
        browser.clickAndSendText(CONFIRM_DELETE_TEXT, repositoryName);
        browser.click(CONFIRM_DELETE_BUTTON);
        return this;
    }

    public GitHubPage logOff(){
        browser.click(PROFILE_BUTTON);
        browser.click(PROFILE_MENU);
        return this;
    }

}
