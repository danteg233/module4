
package git;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


public class GitHubPage extends AbstractPage {
    //TODO: create xpath with parent child sibling
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
    private static final By PROFILE_BUTTON = By.xpath(".//*[contains(@class, 'header-navlink name tooltipped tooltipped-sw js-menu-target')]");
    private static final By PROFILE_MENU = By.xpath(".//*[@class='logout-form']");
    private static final By CONFIRM_LOG_OUT_LOCATOR = By.xpath("//*[@class='home-hero-signup js-signup-form']");

    public NewFilePage createRepository(String name) throws Exception {
        By temp = By.xpath(".//span[contains(text(), '" + name +"'" + ")]");
        if (isElementPresented(temp)){
            webDriver.findElement(temp).click();
            return new NewFilePage();
        }
        webDriver.findElement(NEW_REPOSITORY_BUTTON).click();
        webDriver.findElement(REPOSITORY_NAME).sendKeys(name);
        webDriver.findElement(INIT_WITH_README_LOCATOR).click();
        if (isElementPresented(ERROR_MESSAGE)){
            throw new Exception("Repository with this name already exist");
        }
        waitForElementEnabled(CREATE_REPOSITORY_BUTTON);
        webDriver.findElement(CREATE_REPOSITORY_BUTTON).click();
        Assert.assertTrue(isElementPresented(By.xpath(".//*[contains(a, '" + name + "')]")));
        return new NewFilePage();
    }

    public GitHubPage deleteRepository(String repositoryName){
        webDriver.findElement(SETTING_BUTTON).click();
        waitForElementEnabled(DELETE_BUTTON);
        webDriver.findElement(DELETE_BUTTON).click();
        waitForElementEnabled(DELETE_MENU);
        webDriver.findElement(CONFIRM_DELETE_TEXT).click();
        webDriver.findElement(CONFIRM_DELETE_TEXT).sendKeys(repositoryName);
        webDriver.findElement(CONFIRM_DELETE_BUTTON).click();
        Assert.assertFalse(isElementPresented(By.xpath(".//span[contains(text(), '" + repositoryName +"')]")));
        return this;
    }

    public void logOff(){
        WebElement button = webDriver.findElement(PROFILE_BUTTON);
        new Actions(webDriver).click(button).build().perform();
        webDriver.findElement(PROFILE_MENU).click();
        Assert.assertTrue(isElementPresented(CONFIRM_LOG_OUT_LOCATOR));
    }

}
