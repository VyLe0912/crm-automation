package page.Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.Objects.User;

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
    By txtMessPassInputSelector = By.xpath("//input[@id='nothing:p']//preceding::span[1]");
    By txtConfPassInputSelector = By.xpath("//input[@id='nothing:cp']//preceding::span[1]");
    By txtMessNameInputSelector = By.xpath("//input[@id='nothing:Name']//preceding::span[1]");
    By txtMessCompanyInputSelector = By.xpath("//input[@id='nothing:Company']//preceding::span[1]");
    By txtMessPhoneInputSelector = By.xpath("//input[@id='nothing:phone']//preceding::span[1]");
    By messEmailExistSelector = By.xpath("//input[@name='nothing:j_idt30']//preceding::p[1]");

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

    public String getLabelRegisterForm() {
        return driver.findElement(labelRegisterInputSelector).getText();
    }

    public void waitForRegisterAppear() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> driver1.findElement(labelRegisterInputSelector).isDisplayed());
    }

    public void deleteAllTextbox() {
        driver.findElement(txbEmailInputSelector).clear();
        driver.findElement(txbPassWordInputSelector).clear();
        driver.findElement(txbConfirmPassInputSelector).clear();
        driver.findElement(txbNameInputSelector).clear();
        driver.findElement(txbCompanyInputSelector).clear();
        driver.findElement(txbPhoneInputSelector).clear();
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

    public void SignUp(RegisterUser registerUser) {
        clickTextBoxEmail();
        enterEmail(registerUser.getEmail());
        enterPassword(registerUser.getPassword());
        enterConfirmPassword(registerUser.getConfirmPassword());
        enterName(registerUser.getName());
        enterCompany(registerUser.getCompany());
        enterPhone(registerUser.getPhone());
        clickBtnRegister();
    }

    public void Register(User user) {
        clickTextBoxEmail();
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        enterConfirmPassword(user.getConfPass());
        enterName(user.getName());
        enterCompany(user.getCompany());
        enterPhone(user.getPhone());
        clickBtnRegister();
    }

    public void waitForRegisterLoading() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessageEmailExist() {
        return driver.findElement(messEmailExistSelector).getText();
    }

    public String getMessagePasswordDontMatch() {
        return driver.findElement(messEmailExistSelector).getText();
    }

    public String getMessageRegisterSuccess() {
        return driver.findElement(messEmailExistSelector).getText();
    }

    public void clickTextBoxEmail() {
        driver.findElement(txbEmailInputSelector).click();
    }

    public void register(String email, String pass, String pass2, String name, String company, String phone) {
        clickTextBoxEmail();
        enterEmail(email);
        enterPassword(pass);
        enterConfirmPassword(pass2);
        enterName(name);
        enterCompany(company);
        enterPhone(phone);
        clickBtnRegister();
    }

}
