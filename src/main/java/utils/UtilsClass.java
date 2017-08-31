package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import business_objects.EMail;

import java.util.List;

public class UtilsClass {
    private static final int wait = 10;

    private static final By EMAIL_LIST_LOCATOR = By.xpath(".//*[contains(@class,'ns-view-messages-item-wrap')]");
    private static final String ATTRIBUTE = "title";
    private static final String TO_CHECK_LOCATOR = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_sender js-message-snippet-sender']//*";
    private static final String SUBJ_CHECK_LOCATOR = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']//*";
    private static final String TEXT_CHECK_LOCATOR = "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_firstline js-message-snippet-firstline']//*";

    private static String elementPosition = ".//*[contains(@class,'ns-view-messages-item-wrap')]";

    public static By assertEmail(EMail eMail, WebDriver webDriver){
        waitForElementVisible(EMAIL_LIST_LOCATOR, webDriver);
        List<WebElement> tempList = webDriver.findElements(EMAIL_LIST_LOCATOR);
        for(int i=1; i<=tempList.size(); i++){
            String temp = elementPosition + '[' + i + ']';
            if(webDriver.findElement(By.xpath(temp + TO_CHECK_LOCATOR)).getAttribute(ATTRIBUTE).equalsIgnoreCase(eMail.getTo())
                    && webDriver.findElement(By.xpath(temp + SUBJ_CHECK_LOCATOR)).getAttribute(ATTRIBUTE).equalsIgnoreCase(eMail.getObj())
                    && webDriver.findElement(By.xpath(temp + TEXT_CHECK_LOCATOR)).getAttribute(ATTRIBUTE).equalsIgnoreCase(eMail.getText())){
                return By.xpath(temp);
            }
        }
        return null;
    }

    private static void waitForElementVisible(By locator, WebDriver webDriver){
        new WebDriverWait(webDriver, wait).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
