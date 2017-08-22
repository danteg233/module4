package tests.banana;

import core.AbstractPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class YouTubePage extends AbstractPage {

    private static final By YOU_TUBE_LOGO_LOCATOR = By.xpath("//a[@title='YouTube Home']");
    private static final By VIEWS_LOCATOR = By.xpath(".//*[@class='watch-view-count']");

    public void countViews(){
        assertTrue(isElementPresented(YOU_TUBE_LOGO_LOCATOR));
        Double res = Double.parseDouble(webDriver.findElement(VIEWS_LOCATOR).getText().replaceAll("[^\\d]", ""));
        Assert.assertTrue(res > 5000000, "Here's less than 50 millions views");
    }

}
