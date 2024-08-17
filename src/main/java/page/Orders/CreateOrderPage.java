package page.Orders;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.SideBar.SideBar;

public class CreateOrderPage extends SideBar {
    WebDriver driver;
    By tableRowSelector = By.xpath("//tbody/tr");

    public CreateOrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get number of row product displayed")
    public int countRowProduct() {
        return driver.findElements(tableRowSelector).size();
    }
}
