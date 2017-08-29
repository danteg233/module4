package tests.yandex.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;

public class DragDropPage extends AbstractPage {

    private static final By FIRST_ITEM_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][1]");
    private static final By TRASH_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][last()]");
    private static final By PROGRESSBAR = By.xpath(".//*[@class='b-progressbar']");


    public DragDropPage dragAndDrop(){
        browser.highlightElement(FIRST_ITEM_LOCATOR);
        browser.highlightElement(TRASH_LOCATOR);
        browser.unHighlightElement(TRASH_LOCATOR);
        browser.unHighlightElement(FIRST_ITEM_LOCATOR);
        browser.dragDrop(FIRST_ITEM_LOCATOR, TRASH_LOCATOR);
        return this;
    }

    public boolean isProgressbarPresented(){
        return browser.isElementPresented(PROGRESSBAR);
    }
}
