package core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShoter {
    private static final String SCREENSHOTS_NAME_TML = "screenshots/scr";

    public static void takeScreenshot(){
        WebDriver webDriver = WebDriverSingleton.getWebDriverInstance();
        File screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        try{
            String screenshotName = SCREENSHOTS_NAME_TML + System.nanoTime();
            File copy = new File(screenshotName + ".png");
            FileUtils.copyFile(screenshot, copy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
