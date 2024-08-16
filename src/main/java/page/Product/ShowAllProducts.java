package page.Product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShowAllProducts {
    WebDriver driver;

    By btnNewProductInputSelector;
    By labelShowProductPageInputSelector = By.xpath("//div[@class='ibox-title']/h5");
    By productNameLabelSelector = By.xpath("//tr/td[1]");
    By productPriceLabelSelector = By.xpath("//tr/td[2]");
    By productDiscountLabelSelector = By.xpath("//tr/td[3]");

    By btnFirstPageInputSelector;
    By btnPreviousPageInputSelector;
    By btnNextPageInputSelector;
    By btnLastPageInputSelector;

    public ShowAllProducts(WebDriver driver) {
        this.driver = driver;
    }

    public boolean showAllProductsPageIsDisplayed() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(driver1 -> driver1.findElement(labelShowProductPageInputSelector).isDisplayed());
    }

    public String getProductNameByIndex(int i) {
        return driver.findElements(productNameLabelSelector).get(i-1).getText();
    }

    public String getProductPriceByIndex(int i) {
        return driver.findElements(productPriceLabelSelector).get(i-1).getText();
    }

    public String getProductDiscountByIndex(int i) {
        return driver.findElements(productDiscountLabelSelector).get(i-1).getText();
    }

}

