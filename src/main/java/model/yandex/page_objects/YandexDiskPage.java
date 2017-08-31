package model.yandex.page_objects;

import core.AbstractPage;
import org.openqa.selenium.By;

public class YandexDiskPage extends AbstractPage {
    private static final By TRASH_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][last()]");
    private static final By PROGRESSBAR = By.xpath(".//*[@class='b-progressbar']");
    private static final By FIRST_ITEM_LOCATOR = By.xpath(".//*[@data-metrika-dblclick='count'][1]");
    private static final By OPEN_TRASH_BUTTON = By.xpath(".//*[@href='/client/trash']");
    private static final By RESTORE_BUTTON = By.xpath(".//*[@data-click-action='resource.restore']");
    private static final By PROFILE_TOOLBAR = By.xpath(".//*[@class='header__user']");
    private static final By LOG_OUT_CONFIRM = By.xpath("//*[@class='home-logo__default']");
    private static final By LOG_OUT_BUTTON = By.xpath(".//div[@class='popup2 popup2_theme_normal popup2_direction_bottom-right popup2_visible_yes popup2_target_anchor popup2_autoclosable']//*[@class='menu__list-item'][last()]");

    public void restoreElement(){
        browser.click(OPEN_TRASH_BUTTON);
        browser.click(FIRST_ITEM_LOCATOR);
        browser.click(RESTORE_BUTTON);
    }

    public YandexDiskPage dragAndDrop(){
        browser.highlightElement(FIRST_ITEM_LOCATOR);
        browser.highlightElement(TRASH_LOCATOR);
        browser.unHighlightElement(TRASH_LOCATOR);
        browser.unHighlightElement(FIRST_ITEM_LOCATOR);
        browser.dragDrop(FIRST_ITEM_LOCATOR, TRASH_LOCATOR);
        return this;
    }

    public YandexDiskPage lofOff(){
        browser.click(PROFILE_TOOLBAR);
        browser.click(LOG_OUT_BUTTON);
        return this;
    }


    public boolean isLogOutConfirm(){return browser.isElementPresented(LOG_OUT_CONFIRM);}
    public boolean isProgressbarPresented(){
        return browser.isElementPresented(PROGRESSBAR);
    }
}
