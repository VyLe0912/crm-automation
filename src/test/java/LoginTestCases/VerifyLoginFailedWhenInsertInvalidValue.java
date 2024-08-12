package LoginTestCases;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.utils.ConfigReader;

public class VerifyLoginFailedWhenInsertInvalidValue {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        actions = new Actions(driver);
    }

    @Test
    public void VerifyLoginFailedWhenInsertInvalidValue() throws InterruptedException {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Test invalid email");
        loginPage.login("abcmskldmgmail.com", "VyLe123!");
        softAssert.assertEquals(loginPage.emailTextMessage(), "The email is not valid (ex: abc@abc)", "Error");
        loginPage.deleteTxbEmail();

        // viet them method cho
        loginPage.login("abc@abcgmail.com", "VyLe123!");
        softAssert.assertEquals(loginPage.noticeInvalid(), "The email or password is incorrect!", "Error message 1");
        loginPage.deleteTxbEmail();

        loginPage.login("abc@gmail.com", "VyLe");
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
