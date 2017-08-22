package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver webDriver;
    private static final int wait = 10;

    protected AbstractPage() {
        this.webDriver = WebDriverSingleton.getWebDriverInstance();
    }

    protected boolean isElementPresented(By locator){
        return !webDriver.findElements(locator).isEmpty();
    }

    protected void waitForElementPresent(By locator){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementVisible(By locator){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementEnabled(By locator){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void highlightElement(By locator){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].style.border='3px solid green'", webDriver.findElement(locator));
    }

    protected void unHighlightElement(By locator){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].style.border='0px'", webDriver.findElement(locator));
    }

}
