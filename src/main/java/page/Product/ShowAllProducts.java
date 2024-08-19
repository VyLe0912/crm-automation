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
    By messNoFoundSelector = By.xpath("//tr[@class='ui-widget-content ui-datatable-empty-message']/td");

    //Text box at Column
    By txbColumnProductNameSelector = By.xpath("(//tr/th/input)[1]");
    By txbColumnProductPriceSelector = By.xpath("(//tr/th/input)[2]");
    By txbColumnProductDiscountSelector = By.xpath("(//tr/th/input)[3]");

    //Column of table
    By getColumnProductNameSelector = By.xpath("//tbody/tr/td");
    By getColumnProductPriceSelector = By.xpath("//tbody/tr/td[2]");
    By getColumnProductDiscountSelector = By.xpath("//tbody/tr/td[3]");

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

    public void enterProductNameColumn(String name) {
        driver.findElement(txbColumnProductNameSelector).sendKeys(name);
    }

    public void enterProductPriceColumn(String price) {
        driver.findElement(txbColumnProductPriceSelector).sendKeys(price);
    }

    public void enterProductDiscountColumn(String discount) {
        driver.findElement(txbColumnProductDiscountSelector).sendKeys(discount);
    }

    public boolean allProductsAre(String name) {
        return driver.findElements(getColumnProductNameSelector).equals(name);
    }
    public boolean allProductsStartWiths(String name) {
        return driver.findElement(getColumnProductNameSelector).getText().startsWith(name);
    }

    public String getMessageNoFound() {
        return driver.findElement(messNoFoundSelector).getText();
    }
}

