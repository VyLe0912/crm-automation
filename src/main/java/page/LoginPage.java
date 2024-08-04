package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By txbEmailInputSelector = By.xpath("//input[@id='campaigntypeform:email']");
    By txbPassInputSelector = By.xpath("//input[@id='campaigntypeform:pass']");
    By btnLoginSelector = By.xpath("//input[@name='campaigntypeform:j_idt14']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(txbEmailInputSelector).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(txbPassInputSelector).sendKeys(password);
    }

    public void clickButtonLogin() {
        driver.findElement(btnLoginSelector).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickButtonLogin();
    }
}
