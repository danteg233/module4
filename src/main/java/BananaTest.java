
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BananaTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws Exception {
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//input[@id='lst-ib']")).clear();
        driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("banana song");
        driver.findElement(By.xpath("//input[@name='btnK']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Minions Banana Song Full Song - YouTube')]")).click();
        try{
            assertTrue(driver.findElement(By.xpath("//a[@title='YouTube Home']")).isDisplayed());
        }catch (Exception e){
            System.err.println("It's not YouTube WebSite!");
        }
        String str = driver.findElement(By.xpath(".//*[@class=\"watch-view-count\"]")).getText().replaceAll("[^\\d,]", "");
        Pattern pattern = Pattern.compile("^(([5-9]\\d{1,3},)(\\d{3}(,\\d{3})+)|\\d+)$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.lookingAt()){
            System.out.println("Here's more than 50 million views!");
        }
        else {
            System.out.println("Here's less than 50 million views!");
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    /*
    ^(([5-9]\d{1,3},)(\d{3}(,\d{3})+)|\d+)$ -my regex
     */

}
