package page.Product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProduct extends CreateProduct{

    By btnSaveEditProductSelector = By.xpath("//input[@name='bookForm:j_idt79']");
    By btnCancelEditProductSelector = By.name("bookForm:j_idt79");

    public EditProduct(WebDriver driver) {
        super(driver);
    }

    public void clickBtnSaveEditProduct() {
        driver.findElement(btnSaveEditProductSelector).click();
    }

    public void clickBtnCancelEditProduct() {
        driver.findElement(btnCancelEditProductSelector).click();
    }

    public void editProduct(ProductForm productForm) {
        enterProductName(productForm.getName());
        enterProductPrice(productForm.getPrice());
        enterProductDiscount(productForm.getDiscount());
        clickBtnSaveEditProduct();
    }

}
