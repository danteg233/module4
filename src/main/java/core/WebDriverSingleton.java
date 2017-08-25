package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.mail.webdriver.CustomWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {
    private static WebDriver instance;

    private WebDriverSingleton(){
    }

    public static WebDriver getWebDriverInstance(){
        if(instance != null){
            return instance;
        }
        return instance = init();
    }

    public static void goTo(String url){
        if (instance==null){
            instance = init();
        }
        instance.get(url);
    }

    private static WebDriver init(){
        WebDriver driver = null;
        PropertyFileReader.read("browser");
        String browser = PropertyFileReader.getValue();
        if(browser.equalsIgnoreCase("CHROME")){
            driver = new ChromeDriver();
        }
        if(browser.equalsIgnoreCase("FIREFOX")){
            driver = new FirefoxDriver();
        }
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        WebDriver driver = null;
//        try{
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            driver = new RemoteWebDriver(new URL("http://10.12.9.79:4444/wd/hub"), capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void kill(){
        if(instance != null){
            try{
                instance.quit();
            }catch (Exception e){
                System.out.println("Cannot close browser!");
            }finally {
                instance = null;
            }
        }
    }

}
