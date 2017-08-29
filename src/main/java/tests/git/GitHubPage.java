
package tests.git.page_objects;

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

    public NewFilePage createRepository(String name) throws Exception {
        By temp = By.xpath(".//span[contains(text(), '" + name +"'" + ")]");
        if (isElementPresented(temp)){
            click(temp);
            return new NewFilePage();
        }
        click(NEW_REPOSITORY_BUTTON);
        clickAndSendText(REPOSITORY_NAME, name);
        click(INIT_WITH_README_LOCATOR);
        if (isElementPresented(ERROR_MESSAGE)){
            MyLogger.warn("Repository with this name already exist");
            throw new Exception("Repository with this name already exist");
        }
        click(CREATE_REPOSITORY_BUTTON);
        return new NewFilePage();
    }

    public boolean isRepositoryCreated(String name){
        return isElementPresented(By.xpath(".//*[contains(a, '" + name + "')]"));
    }

    public boolean isRepositoryDeleted(String name){
        return isElementPresented(By.xpath(".//span[contains(text(), '" + name +"')]"));
    }

    public boolean isLogOutConfirmed(){
        return isElementPresented(CONFIRM_LOG_OUT_LOCATOR);
    }



    public GitHubPage deleteRepository(String repositoryName){
        click(SETTING_BUTTON);
        click(DELETE_BUTTON);
        waitForElementEnabled(DELETE_MENU);
        clickAndSendText(CONFIRM_DELETE_TEXT, repositoryName);
        click(CONFIRM_DELETE_BUTTON);
        return this;
    }

    public void logOff(){
        click(PROFILE_BUTTON);
        click(PROFILE_MENU);
    }

}
