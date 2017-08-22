package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    private static WebDriver init(){
//        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new FirefoxDriver();
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
