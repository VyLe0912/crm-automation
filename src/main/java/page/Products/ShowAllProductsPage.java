package page.Products;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.Customer.ShowAllCustomersPage;

import java.time.Duration;

public class ShowAllProductsPage extends ShowAllCustomersPage {
    WebDriver driver;



    public ShowAllProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public int getTotalProduct() {
        return ((getTotalPage() - 1)*10 + countRowInCurrentPage());
    }

    public void waitForLastPageButtonIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d->d.findElement(lastPageButtonInTopSelector).isDisplayed());
    }

    @Step("Click last customer page button")
    public void openLastProductPage() {
        driver.findElement(lastPageButtonInTopSelector).click();
    }
}
