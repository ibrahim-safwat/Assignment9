package pages;

import driverfactory.Driver;
import org.openqa.selenium.By;

import java.time.Duration;

public class FinalizingOrderPage {
    private Driver driver;
    private By finishButton = By.id("finish");


    /***************************** constructors ****************************/

    public FinalizingOrderPage(Driver driver) {
        this.driver = driver;
    }

    /***************************** methods ****************************/
    public OrderCompletePage clickFinishButton() {
        System.out.println("Order completed");
        driver.element().Click(finishButton);
        return new OrderCompletePage(driver);
    }

    /***************************** Assertions ****************************/

    public FinalizingOrderPage assertOrderCompletePage() {
        System.out.println("Order completed");
        driver.element().WaitForElementToBeVisible(finishButton, Duration.ofSeconds(4));
        driver.hardassertions().element(finishButton).IsDisplayed();
        return this;
    }

}
