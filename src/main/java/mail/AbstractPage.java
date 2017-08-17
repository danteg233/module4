package mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    WebDriver webDriver;
    private static final int wait = 7;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean isElementPresented(By locator){
        return !webDriver.findElements(locator).isEmpty();
    }

    public void waitForElementPresent(By locator){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementVisible(By locator){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementEnabled(By locator){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.elementToBeClickable(locator));
    }

}
