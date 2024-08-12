package UpdateProfileCases;

import io.qameta.allure.Allure;
import page.ProfilePage.ProfilePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyOpenProfilePage {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    public void VerifyOpenProfilePage() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Login function");
        loginPage.loginFunction();
        homePage.openHomePage();

        Allure.step("Open profile page");
        profilePage.clickLinkProfile();
        profilePage.waitForProfilePageAppear();
        softAssert.assertEquals(profilePage.labelProfilePage(), "User Information", "Can't open profile page");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ProfilePage profilePage;
    LoginPage loginPage;
    HomePage homePage;
    ConfigReader configReader;
    SoftAssert softAssert;
}
