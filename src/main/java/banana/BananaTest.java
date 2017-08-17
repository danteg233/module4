package banana;

import mail.PropertyFileReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BananaTest {
    private WebDriver webDriver;
    private String baseUrl;

    @BeforeClass
    public void setUp(){
        webDriver = new FirefoxDriver();
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        PropertyFileReader.read("bananaBaseUrl");
        baseUrl = PropertyFileReader.getBaseUrl();
    }

    @Test
    public void test(){
        webDriver.get(baseUrl);
        YouTubePage youTubePage = new SearchPage(webDriver).searchInput("banana song").searchVideo();
        youTubePage.countViews();
    }

    @AfterClass
    public void tearDown(){
        webDriver.quit();
    }
}
