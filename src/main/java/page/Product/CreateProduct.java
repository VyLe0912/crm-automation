package page.Product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateProduct {
    WebDriver driver;

    By txbProductNameInputSelector;
    By txbProductPriceInputSelector;
    By txbProductDiscountInputSelector;
    By btnSaveProductInputSelector;
    By labelCreateProductPageInputSelector = By.xpath("//div[@class='ibox-title']/h5");

    //Messages
    By getTxtMessProductInputSelector;
    By getTxtMessProductPriceInputSelector;
    By getTxtMessProductDiscountInputSelector;

    public CreateProduct(WebDriver driver) {
        this.driver = driver;
    }

    public void enterProductName() {
    }

    public void enterProductPrice() {
    }

    public void enterProductDiscount() {
    }

    public void clickBtnSave() {
    }

    public void createProduct() {
    }

    public String getLabelCreateProductPage() {
        return driver.findElement(labelCreateProductPageInputSelector).getText();
    }

    public boolean createProductPageIsDisplay() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(labelCreateProductPageInputSelector).isDisplayed());
    }
}
