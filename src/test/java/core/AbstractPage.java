package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
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

    protected void clickAndSendText(By locator, String text){
        waitForElementPresent(locator);
        MyLogger.info("Clicking element (Located: " + locator + ")");
        MyLogger.info("Sending '" + text + "' to element (Located: " + locator + ")");
        new Actions(webDriver).click(webDriver.findElement(locator)).sendKeys(text).build().perform();
    }

    protected void click(By locator){
        try {
            waitForElementEnabled(locator);
            MyLogger.info("Clicking element (Located: " + locator + ")");
            new Actions(webDriver).click(webDriver.findElement(locator)).build().perform();
        }catch (StaleElementReferenceException ex){
            MyLogger.warn("Couldn't find an element with locator " + locator);
            MyLogger.info("Looking for it again...");
            new Actions(webDriver).click(webDriver.findElement(locator)).build().perform();
        }
    }

    protected void dragDrop(By item, By target){
        waitForElementVisible(item);
        waitForElementVisible(target);
        new Actions(webDriver).dragAndDrop(webDriver.findElement(item), webDriver.findElement(target)).build().perform();
    }

}
