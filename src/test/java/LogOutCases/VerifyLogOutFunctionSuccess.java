package LogOutCases;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import page.utils.ConfigReader;

public class VerifyLogOutFunctionSuccess {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyOpenLogOutModal() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Login funciton");
        loginPage.loginFunction();
        homePage.clickBtnLogOut();// doi action log out

        Allure.step("Verify Log out function");
        softAssert.assertEquals(loginPage.getLabelWebsite(), "CRM DEMO", "log out failed" );
        softAssert.assertAll();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    ConfigReader configReader;
    SoftAssert softAssert;
}
