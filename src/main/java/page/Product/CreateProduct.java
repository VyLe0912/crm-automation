package page.Product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateProduct {
    WebDriver driver;

    By txbProductNameInputSelector = By.xpath("//input[@id='bookForm:pn']");
    By txbProductPriceInputSelector = By.xpath("//input[@id='bookForm:pp']");
    By txbProductDiscountInputSelector = By.xpath("//input[@id='bookForm:pd']");
    By btnSaveProductInputSelector = By.xpath("//input[@name='bookForm:j_idt77']");
    By labelCreateProductPageInputSelector = By.xpath("//div[@class='ibox-title']/h5");

    //Messages
    By textMessProductInputSelector = By.xpath("//input[@id='bookForm:pn']//preceding::span[1]");
    By textMessProductPriceInputSelector = By.xpath("//input[@id='bookForm:pp']//preceding::span[1]");
    By textMessProductDiscountInputSelector = By.xpath("//input[@id='bookForm:pd']//preceding::span[1]");

    public CreateProduct(WebDriver driver) {
        this.driver = driver;
    }

    public void enterProductName(String name) {
        driver.findElement(txbProductNameInputSelector).sendKeys(name);
    }

    public void enterProductPrice(String price) {
        driver.findElement(txbProductPriceInputSelector).sendKeys(price);
    }

    public void enterProductDiscount(String discount) {
        driver.findElement(txbProductDiscountInputSelector).sendKeys(discount);
    }

    public void clickBtnSave() {
        driver.findElement(btnSaveProductInputSelector).click();
    }

    public void waitToDownloadPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void createProduct(ProductForm productForm) {
        enterProductName(productForm.getName());
        enterProductPrice(productForm.getPrice());
        enterProductDiscount(productForm.getDiscount());
        waitToDownloadPage();
        clickBtnSave();
    }

    public String getLabelCreateProductPage() {
        return driver.findElement(labelCreateProductPageInputSelector).getText();
    }

    public String getMessProName() {
        return driver.findElement(textMessProductInputSelector).getText();
    }

    public String getMessProPrice() {
        return driver.findElement(textMessProductPriceInputSelector).getText();
    }

    public String getMessProDiscount() {
        return driver.findElement(textMessProductDiscountInputSelector).getText();
    }

    public boolean createProductPageIsDisplay() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(d->d.findElement(labelCreateProductPageInputSelector).isDisplayed());
    }
}
