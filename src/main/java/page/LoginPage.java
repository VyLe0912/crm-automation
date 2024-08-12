package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.utils.ConfigReader;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    ConfigReader configReader;

    By txbEmailInputSelector = By.xpath("//input[@id='campaigntypeform:email']");
    By txbPassInputSelector = By.xpath("//input[@id='campaigntypeform:pass']");
    By btnLoginSelector = By.xpath("//input[@name='campaigntypeform:j_idt14']");
    By textEmailSelector = By. xpath("//input[@id='campaigntypeform:email']//preceding::span");
    By textPassSelector = By.xpath("//input[@id='campaigntypeform:pass']//preceding::span[1]");
    By noticeInvalidEmail = By.xpath("(//div[@class='col-sm-4 col-md-offset-4 pull-right']/p)[1]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void deleteTxbEmail() {
        driver.findElement(txbEmailInputSelector).clear();
    }

    public void deleteTxbPass() {
        driver.findElement(txbPassInputSelector).clear();
    }

    public String noticeInvalid() {
        return driver.findElement(noticeInvalidEmail).getText();
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
        return driver.findElement(textPassSelector).getText();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickButtonLogin();
    }

    public void loginFunction() {
        configReader = new ConfigReader();
        enterEmail(configReader.email());
        enterPassword(configReader.password());
        clickButtonLogin();
    }
}
