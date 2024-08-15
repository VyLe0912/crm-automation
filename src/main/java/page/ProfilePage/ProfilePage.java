package page.ProfilePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.LoginPage;
import page.Objects.User;
import page.SideBar;
import utils.ConfigReader;

import java.time.Duration;

public class ProfilePage {
    WebDriver driver;
    LoginPage loginPage;
    SideBar sideBar;
    ConfigReader configReader;


    By linkProfileSelector = By.xpath("//span[@class='text-muted text-xs block']/a");
    By txbNameInputSelector = By.xpath("//input[@id='campaigntypeform:Name']");
    By txbCompanyInputSelector = By.xpath("//input[@id='campaigntypeform:Company']");
    By txbPhoneInputSelector = By.xpath("//input[@id='campaigntypeform:phone']");
    By btnSaveProfileSelector = By.xpath("//input[@name='campaigntypeform:j_idt79']");
    By textMessNameSelector = By.xpath("//input[@id='campaigntypeform:Name']//preceding::span[1]");
    By textMessCompanySelector = By.xpath("//input[@id='campaigntypeform:Company']//preceding::span[1]");
    By textMessPhoneSelector = By.xpath("//input[@id='campaigntypeform:phone']//preceding::span[1]");
    By labelProfilePageSelector = By.xpath("//div[@class='ibox-title']/h5");
    By notifySuccessSelector = By.xpath("//input[@name='campaigntypeform:j_idt79']//preceding::p");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLinkProfile() {
        driver.findElement(linkProfileSelector).click();
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

    public void clickBtnSaveProfile() {
        driver.findElement(btnSaveProfileSelector).click();
    }

    public String labelProfilePage() {
        return driver.findElement(labelProfilePageSelector).getText();
    }

    public void waitForProfilePageAppear() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> driver1.findElement(labelProfilePageSelector).isDisplayed());
    }

    public void waitForDownload() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> driver1.findElement(notifySuccessSelector).isDisplayed());
    }

    public String textMessageUpdateSuccess() {
        return driver.findElement(notifySuccessSelector).getText();
    }

    public void updateProfile(User user) {
        enterName(user.getName());
        enterCompany(user.getCompany());
        enterPhone(user.getPhone());
        clickBtnSaveProfile();
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){}
    }

    public void deleteTxbName() {
        driver.findElement(txbNameInputSelector).clear();
    }

    public void deleteTxbCompany() {
        driver.findElement(txbCompanyInputSelector).clear();
    }

    public void deleteTxbPhone() {
        driver.findElement(txbPhoneInputSelector).clear();
    }

    public void deleteAllTextBox() {
        driver.findElement(txbNameInputSelector).clear();
        driver.findElement(txbCompanyInputSelector).clear();
        driver.findElement(txbPhoneInputSelector).clear();
    }

    public void progressOpenProfile() {
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        configReader = new ConfigReader();
        loginPage.loginFunction();
        sideBar.openHomePage();
        clickLinkProfile();
        waitForProfilePageAppear();
        deleteAllTextBox();
    }

    public void waitForNoticeAppear() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> driver1.findElement(textMessNameSelector).getText());
    }

    public String nameTextMessage() {
        return driver.findElement(textMessNameSelector).getText();
    }

    public String companyTextMessage() {
        return driver.findElement(textMessCompanySelector).getText();
    }

    public String phoneTextMessage() {
        return driver.findElement(textMessPhoneSelector).getText();
    }

}
