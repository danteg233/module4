package tests.yandex.business_objects;

import core.AbstractPage;
import core.ScreenShoter;
import org.openqa.selenium.By;

public class DragDropPage extends AbstractPage {

    private static final By FIRST_ITEM_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][1]");
    private static final By TRASH_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][last()]");
    private static final By PROGRESSBAR = By.xpath(".//*[@class='b-progressbar']");


    public DragDropPage dragAndDrop(){
        highlightElement(FIRST_ITEM_LOCATOR);
        highlightElement(TRASH_LOCATOR);
        unHighlightElement(TRASH_LOCATOR);
        unHighlightElement(FIRST_ITEM_LOCATOR);
        dragDrop(FIRST_ITEM_LOCATOR, TRASH_LOCATOR);
        return this;
    }

    public boolean isProgressbarPresented(){
        return isElementPresented(PROGRESSBAR);
    }
}
