package LoginTestCases;

import io.qameta.allure.Allure;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyLoginFailedWhenInsertInvalidValue {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void VerifyLoginFailedWhenInsertInvalidValue() throws InterruptedException {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1920, 1080));

        Allure.step("Test invalid email");
        loginPage.login("abcmskldmgmail.com", "VyLe123!");
        softAssert.assertEquals(loginPage.emailTextMessage(), "The email is not valid (ex: abc@abc)", "Error");
        loginPage.deleteTxbEmail();

        loginPage.login("abc@abcgmail.com", "VyLe123!");
        Thread.sleep(3000);
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error message 1");
        loginPage.deleteTxbEmail();

        loginPage.login("abc@gmail.com", "VyLe");
        Thread.sleep(3000);
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error mess 2");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    SoftAssert softAssert;
    Actions actions;
}
