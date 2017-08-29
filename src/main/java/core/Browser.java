package core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reporting.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {
    private WebDriver webDriver;
    private static Browser instance;

    private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 15;
    private static final int IMPLICITLY_DEFAULT_TIMEOUT_SECONDS = 10;
    private static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    private Browser(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public static Browser getInstance(){
        if(instance == null){
            return instance = init();
        }
        return instance;
    }

    public static WebDriver getWebDriver() {
        return instance.webDriver;
    }

    private static Browser init() {
        WebDriver driver = null;
        PropertyFileReader.read("browser");
        String browser = PropertyFileReader.getValue();
        if(browser.equalsIgnoreCase("CHROME")){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        if(browser.equalsIgnoreCase("FIREFOX")){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
//        WebDriver driver = null;
//        try{
//            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//            driver = new RemoteWebDriver(new URL("http://10.12.9.79:4444/wd/hub"), capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return new Browser(driver);
    }

    public boolean isElementPresented(By locator){
        return !webDriver.findElements(locator).isEmpty();
    }

    public void waitForElementPresent(By locator){
        new WebDriverWait(webDriver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementVisible(By locator){
        new WebDriverWait(webDriver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitForElementEnabled(By locator){
        new WebDriverWait(webDriver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void highlightElement(By locator){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].style.border='3px solid green'", webDriver.findElement(locator));
    }

    public void unHighlightElement(By locator){
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].style.border='0px'", webDriver.findElement(locator));
    }

    public void clickAndSendText(By locator, String text){
        waitForElementPresent(locator);
        MyLogger.info("Clicking element (Located: " + locator + ")");
        MyLogger.info("Sending '" + text + "' to element (Located: " + locator + ")");
        new Actions(webDriver).click(webDriver.findElement(locator)).sendKeys(text).build().perform();
    }

    public void clear(By locator){
        webDriver.findElement(locator).clear();
    }

    public void click(By locator){
        try {
            waitForElementEnabled(locator);
            MyLogger.info("Clicking element (Located: " + locator + ")");
            new Actions(webDriver).click(webDriver.findElement(locator)).build().perform();
        }catch (StaleElementReferenceException ex){
            MyLogger.warn("Couldn't find an element with locator " + locator);
            MyLogger.info("Looking for it again...");
            new Actions(webDriver).click(webDriver.findElement(locator)).build().perform();
        }
    }

    public void dragDrop(By item, By target){
        waitForElementVisible(item);
        waitForElementVisible(target);
        new Actions(webDriver).dragAndDrop(webDriver.findElement(item), webDriver.findElement(target)).build().perform();
    }

    public  void goTo(String url){
        MyLogger.info("Going to " + url);
        instance.webDriver.get(url);
    }

    public static void takeScreenshot(){
        File screenshot = ((TakesScreenshot)instance.webDriver).getScreenshotAs(OutputType.FILE);
        try{
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
            MyLogger.info("Screenshot has been successfully taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void kill(){
        if(instance != null){
            try{
                instance.webDriver.quit();
            }catch (Exception e){
                System.out.println("Cannot close browser!");
            }finally {
                instance = null;
            }
        }
    }
}
