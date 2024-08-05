package LoginTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfigReader;

public class VerifyLoginFailedWhenAllFieldsAreBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenAllFieldsAreBlank() {
        driver.get(configReader.getUrl());
        loginPage.login("","");
        loginPage.waitForTextAppear();

        //Kiểm tra thông báo trường Email hiển thị
        softAssert.assertEquals(loginPage.emailTextMessage(), "Please enter your email", "Email field error");

        //Kiểm tra thông báo trường Pass hiển thị
        softAssert.assertEquals(loginPage.passTextMessage(), "Please enter your password", "Password field error");

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
}
