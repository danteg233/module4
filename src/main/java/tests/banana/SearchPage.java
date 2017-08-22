package banana;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends AbstractPage {

    private static final By INPUT_LOCATOR = By.xpath("//input[@id='lst-ib']");
    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//input[@name='btnK']");
    private static final By LINK_LOCATOR = By.xpath("//a[contains(text(),'Minions Banana Song Full Song - YouTube')]");

    public SearchPage searchInput(String string){
        webDriver.findElement(INPUT_LOCATOR).click();
        webDriver.findElement(INPUT_LOCATOR).sendKeys(string);
        webDriver.findElement(SEARCH_BUTTON_LOCATOR).click();
        return this;
    }

    public YouTubePage searchVideo(){
        waitForElementEnabled(LINK_LOCATOR);
        webDriver.findElement(LINK_LOCATOR).click();
        return new YouTubePage();
    }
}
