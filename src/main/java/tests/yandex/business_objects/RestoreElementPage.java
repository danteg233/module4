package tests.yandex.business_objects;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class RestoreElementPage extends AbstractPage {
        private static final By FIRST_ITEM_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][1]");
        private static final By OPEN_TRASH_BUTTON = By.xpath(".//*[@href='/client/trash']");
        private static final By RESTORE_BUTTON = By.xpath(".//*[@data-click-action='resource.restore']");

        public void restoreElement(){
        waitForElementVisible(OPEN_TRASH_BUTTON);
        new Actions(webDriver).click(webDriver.findElement(OPEN_TRASH_BUTTON)).build().perform();
        waitForElementEnabled(FIRST_ITEM_LOCATOR);
        new Actions(webDriver).click(webDriver.findElement(FIRST_ITEM_LOCATOR)).build().perform();
        waitForElementVisible(RESTORE_BUTTON);
        new Actions(webDriver).click(webDriver.findElement(RESTORE_BUTTON)).build().perform();
    }
}
