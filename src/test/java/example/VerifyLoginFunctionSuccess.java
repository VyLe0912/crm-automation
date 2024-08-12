package example;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import page.Objects.User;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyLoginFunctionSuccess {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
        user = new User("abc@gmail.com", "VyLe123!");
    }

    @Test
    public void verifyLoginFunction() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Verify Login fucntion success");
        loginPage.loginFunc(user);
        softAssert.assertEquals(homePage.headerNameAccount(), "Thy", "Login failed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    HomePage homePage;
    User user;
    SoftAssert softAssert;
}
