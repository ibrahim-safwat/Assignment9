package utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AllureReportHelper {


    public static void cleanAllureReport() {
        try {
            FileUtils.deleteDirectory(new File("target/allure-results"));
            System.out.println("Allure Report cleaned successfully");
        } catch (IOException e) {
            System.out.println("Allure Report Already Cleaned");
        }
    }

    public static void openAllureReport() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("zsh", "-c", "allure serve target/allure-results");
            processBuilder.start();
            System.out.println("Allure Report opened successfully");
        } catch (IOException e) {
            System.out.println("Unable to open Allure Report");
        }
    }
}

