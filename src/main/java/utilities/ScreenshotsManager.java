package utilities;

import driverfactory.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ScreenshotsManager {
        public static void TakeScreenShot(WebDriver driver, String screenshotName) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Path.of("Screenshots", screenshotName + "_Failed_" + System.currentTimeMillis() + ".png");

            try {
                Files.createDirectories(destination.getParent());
                Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Screenshot: " + destination.toAbsolutePath());
        }
    }


