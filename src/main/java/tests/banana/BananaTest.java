package banana;

import core.PropertyFileReader;
import core.WebDriverSingleton;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BananaTest {
    private String baseUrl;

    @BeforeClass
    public void setUp(){
        PropertyFileReader.read("bananaBaseUrl");
        baseUrl = PropertyFileReader.getBaseUrl();
    }

    @Test
    public void test(){
        WebDriverSingleton.getWebDriverInstance().get(baseUrl);
        YouTubePage youTubePage = new SearchPage().searchInput("banana song").searchVideo();
        youTubePage.countViews();
    }

    @AfterClass
    public void tearDown(){
        WebDriverSingleton.kill();
    }
}
