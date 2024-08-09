package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterForm {
    WebDriver driver;

    //Selector thuoc tinh form Register
    By txbEmailInputSelector = By.xpath("//input[@id='nothing:email1']");
    By txbPassWordInputSelector = By.xpath("//input[@id='nothing:p']");
    By txbConfirmPassInputSelector = By.xpath("//input[@id='nothing:cp']");
    By txbNameInputSelector = By.xpath("//input[@id='nothing:Name']");
    By txbCompanyInputSelector = By.xpath("//input[@id='nothing:Company']");
    By txbPhoneInputSelector = By.xpath("//input[@id='nothing:phone']");
    By btnRegisterInputSelector = By.xpath("//input[@name='nothing:j_idt30']");
    By labelRegisterInputSelector = By.xpath("(//div[@class='ibox-title']/h5)[2]");

    //Selector thong bao cua form Register
    By txtMessEmailInputSelector = By.xpath("//input[@id='nothing:email1']//preceding::span");
    By txtMessPassInputSelector = By.xpath("//input[@id='nothing:p']//preceding::span");
    By txtConfPassInputSelector = By.xpath("//input[@id='nothing:cp']//preceding::span[1]");
    By txtMessNameInputSelector = By.xpath("//input[@id='nothing:Name']//preceding::span[1]");
    By txtMessCompanyInputSelector = By.xpath("//input[@id='nothing:Company']//preceding::span[1]");
    By txtMessPhoneInputSelector = By.xpath("//input[@id='nothing:phone']//preceding::span[1]");

    public RegisterForm(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(txbEmailInputSelector).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(txbPassWordInputSelector).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        driver.findElement(txbConfirmPassInputSelector).sendKeys(confirmPassword);
    }

    public void enterName(String name) {
        driver.findElement(txbNameInputSelector).sendKeys(name);
    }

    public void enterCompany(String company) {
        driver.findElement(txbCompanyInputSelector).sendKeys(company);
    }

    public void enterPhone(String phone) {
        driver.findElement(txbPhoneInputSelector).sendKeys(phone);
    }

    public void clickBtnRegister() {
        driver.findElement(btnRegisterInputSelector).click();
    }

    public String labelRegisterForm() {
        return driver.findElement(labelRegisterInputSelector).getText();
    }

    public void waitForRegisterAppear() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> driver1.findElement(labelRegisterInputSelector).isDisplayed());
    }

    public void deleteAllTextbox() {
        driver.findElement(txbEmailInputSelector).clear();
    }

    public String textMessEmail() {
        return driver.findElement(txtMessEmailInputSelector).getText();
    }

    public String textMessPass() {
        return driver.findElement(txtMessPassInputSelector).getText();
    }

    public String textMessConfPass() {
        return driver.findElement(txtConfPassInputSelector).getText();
    }

    public String textMessName() {
        return driver.findElement(txtMessNameInputSelector).getText();
    }

    public String textMessCompany() {
        return driver.findElement(txtMessCompanyInputSelector).getText();
    }

    public String textMessPhone() {
        return driver.findElement(txtMessPhoneInputSelector).getText();
    }

    public void SignUp(RegisterUser user) {
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        enterConfirmPassword(user.getConfirmPassword());
        enterName(user.getName());
        enterCompany(user.getCompany());
        enterPhone(user.getPhone());
        clickBtnRegister();
    }


}
