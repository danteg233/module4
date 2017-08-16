import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
add parameters property files add maven
 */
public class TestClass {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    private String userName, pass, to, subj;
    private String context = "just a test";

    @BeforeClass
    @Parameters({"userName", "pass", "to", "subj"})
    public void setUp(String userName, String pass, String to, String subj) throws Exception {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        PropertyFileReader.read();
        driver = new FirefoxDriver();
        this.userName = userName;
        this.pass = pass;
        this.to = to;
        this.subj = subj;
        baseUrl = PropertyFileReader.getBaseUrl();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws Exception {
        driver.get(baseUrl);
        try{
            logIn(userName, pass);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception("Error!");
        }

        makeADraft(to, subj);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@title='" + subj + "']")).click();
        sendEmail();
        driver.findElement(By.xpath(".//*[contains(@title, 'Отправленные')]")).click();
        try{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            isEmailExist();
            logOff();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {

        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    private void logOff() {
        try{
            driver.findElement(By.xpath(".//*[@title='strong.tt123@yandex.kz']")).click();
            driver.findElement(By.xpath(".//*[@data-metric='Меню сервисов:Выход']")).click();
        }catch (Exception e){
            System.out.println(e.getMessage());
            driver.quit();
        }

    }

    private void isEmailExist() throws Exception {
        try{
            assertTrue(driver.findElement(By.xpath(".//*[@title='" + subj + "']")).isDisplayed());
            assertTrue(driver.findElement(By.xpath(".//*[@title='" + context + "']")).isDisplayed());
            assertTrue(driver.findElement(By.xpath(".//*[@title='" + to + "']")).isDisplayed());
        }catch (Exception e){
            throw new Exception();
        }
    }



    private void sendEmail() {
        try{
            assertEquals(driver.findElement(By.xpath("//input[@name='subj']")).getAttribute("value"), subj);
            driver.findElement(By.xpath(".//*[contains(@title, 'Отправить письмо')]")).click();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void makeADraft(String to, String subj) throws InterruptedException {
        driver.findElement(By.xpath(".//*[@title=\"Написать (w, c)\"]")).click();
        driver.findElement(By.xpath("//div[@name='to']")).sendKeys(to);
        driver.findElement(By.xpath("//input[@name='subj']")).click();
        driver.findElement(By.xpath("//input[@name='subj']")).sendKeys(subj);
        driver.findElement(By.xpath("//div[contains(@role, 'textbox')]")).click();
        driver.findElement(By.xpath("//div[contains(@role, 'textbox')]")).sendKeys(context);
        driver.findElement(By.xpath("//*[contains(@title,'Черновики')]")).click();
        saveChanges();
    }


    private void saveChanges() {
        if (driver.findElement(By.xpath(".//*[@class='_nb-popup-i']")).isDisplayed()) {
            driver.findElement(By.xpath(".//*[@data-action='save']")).click();
        }
    }




    private void logIn(String name, String pass) throws Exception{
        driver.findElement(By.name("login")).click();
        driver.findElement(By.name("login")).sendKeys(name);
        driver.findElement(By.name("passwd")).click();
        driver.findElement(By.name("passwd")).sendKeys(pass);
        driver.findElement(By.xpath(".//*[@type=\"submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);         //change wait time to check if element exist or not
        boolean isPresent = driver.findElements(By.xpath(".//*[@class = 'passport-Domik-Form-Error passport-Domik-Form-Error_active']")).size() > 0;
        if(isPresent){
            throw new Exception("Wrong password or login!");
        }


    }
    /*
    .//*[contains(@class, 'mail-Compose-Message')]//*[@role='textbox']
     */

}
