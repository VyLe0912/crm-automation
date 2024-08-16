package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateOrderPage {
    WebDriver driver;
    By tableRowSelector = By.xpath("//tbody/tr");

    public CreateOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get number of row product displayed")
    public int countRowProduct() {
        return driver.findElements(tableRowSelector).size();
    }
}
