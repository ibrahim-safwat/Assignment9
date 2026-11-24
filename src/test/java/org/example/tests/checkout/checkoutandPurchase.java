package org.example.tests.checkout;

import driverfactory.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utilities.PropertiesManager;
import utilities.ScreenshotsManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class checkoutandPurchase {

    String status;
    ThreadLocal<Driver> driverThreadLocal;

    @BeforeSuite (alwaysRun = true)
    public void beforeSuite() {
        System.out.println("Suite start");
        PropertiesManager.initializeProperties();
    }

//    @BeforeClass(alwaysRun = true)
//    @Parameters ({"browserName"})
//    public void Setup(String browser) {
//        driverThreadLocal = new ThreadLocal<>();
//        driverThreadLocal.set(new Driver(browser));
//        driverThreadLocal.get().getDriver().navigate().to("https://www.saucedemo.com");
//    }
@BeforeClass(alwaysRun = true)
public void Setup() {
    driverThreadLocal = new ThreadLocal<>();
    driverThreadLocal.set(new Driver());
    }

    @BeforeMethod
    public void CreateLoginPage() {
        System.out.println("Moving to the next page");
    }

    @Test(priority = 1, description = "TC03 - Login user and add items to cart then proceed to checkout and complete purchase")
    public void loginUserWithIncorrectCredentialsDraft() {
        new LoginPage(driverThreadLocal.get()).
                EnterLoginCredentials().
                ClickSuccessfulLoginButton().
                addBikeLightToCartButton().
                addBackpackToCartButton().
                goToCartButton().
                getItemCountinCartContainerisTwo().
                removeFirstItemFromCart().
                getItemCountinCartContainerIsOne().
                clickCheckoutButton().
                assertCheckoutPage().
                insertFirstName().
                insertLastName().
                insertZipCode().
                clickContinue().
                assertOrderCompletePage().
                clickFinishButton().
                assertOrderCompletePage().
                clickBackToProductsButton();
    }

    @AfterMethod(alwaysRun = true)
    public void Proof(ITestResult result) throws Exception {
        if(result.getStatus() == ITestResult.FAILURE) {
            // Take screenshot on test failure
            System.out.println("Test failed: " + result.getName());
            // Implement your screenshot logic here
            ScreenshotsManager.TakeScreenShot(driverThreadLocal.get().getDriver(), result.getTestName());
        }
    }

    @AfterClass(alwaysRun = true)
    public void TearDown() {
        driverThreadLocal.get().getDriver().quit();
    }
}


