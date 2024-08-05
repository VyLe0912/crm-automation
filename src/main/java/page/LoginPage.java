package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    By txbEmailInputSelector = By.xpath("//input[@id='campaigntypeform:email']");
    By txbPassInputSelector = By.xpath("//input[@id='campaigntypeform:pass']");
    By btnLoginSelector = By.xpath("//input[@name='campaigntypeform:j_idt14']");
    By textEmailSelector = By. xpath("(//div[@class='col-lg-10'])[1]");
    By textPassSelector = By.xpath("(//div[@class='col-lg-10'])[2]");

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

    public void waitForTextAppear() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver1 -> driver1.findElement(textEmailSelector).getText());
    }

    public String emailTextMessage() {
        return driver.findElement(textEmailSelector).getText();
    }

    public String passTextMessage() {
        return driver.findElement(passTextMessage()).getText();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickButtonLogin();
    }
}
