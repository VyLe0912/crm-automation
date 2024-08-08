package LoginTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfigReader;

public class VerifyLoginFailedWhenOneFieldIsBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyLoginFailedWhenOneFieldIsBlank() {
        driver.get(configReader.getUrl());
        loginPage.login("", "VyLe123!");
        loginPage.waitForTextAppear();

        //Kiểm tra thông báo trường Email hiển thị
        softAssert.assertEquals(loginPage.emailTextMessage(), "Please enter your email", "Email field error");

        loginPage.deleteTxbPass();

        loginPage.login("abc@gmail.com","");

        //Kiểm tra thông báo trường password hiển thị
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
