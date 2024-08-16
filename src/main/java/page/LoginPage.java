package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.Objects.User;
import utils.ConfigReader;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private ConfigReader configReader;

    By txbEmailInputSelector = By.xpath("//input[@id='campaigntypeform:email']");
    By txbPassInputSelector = By.xpath("//input[@id='campaigntypeform:pass']");
    By btnLoginSelector = By.xpath("//input[@name='campaigntypeform:j_idt14']");
    By textEmailSelector = By. xpath("//input[@id='campaigntypeform:email']//preceding::span");
    By textPassSelector = By.xpath("//input[@id='campaigntypeform:pass']//preceding::span[1]");
    By noticeInvalidEmail = By.xpath("(//div[@class='col-sm-4 col-md-offset-4 pull-right']/p)[1]");
    By labelCRMWebsiteInputSelector = By.xpath("//strong[@class='text-navy']");

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

    public String getValueTxbEmail() {
        return driver.findElement(txbEmailInputSelector).getAttribute("value");
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

    public boolean isDisappearLoginForm() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        return wait.until(d->d.findElements(btnLoginSelector).size()==0);
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickButtonLogin();
        isDisappearLoginForm();
    }

    public void loginFunc(User user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickButtonLogin();
    }

    public void loginFunction() {
        configReader = new ConfigReader();
        enterEmail(configReader.email());
        enterPassword(configReader.password());
        clickButtonLogin();
        waitToDownloadPage();
    }

    public void waitToDownloadPage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLabelWebsite() {
        return driver.findElement(labelCRMWebsiteInputSelector).getText();
    }
}
