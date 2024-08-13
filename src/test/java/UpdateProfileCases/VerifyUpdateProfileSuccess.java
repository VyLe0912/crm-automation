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
import page.LoginPage;
import page.SideBar;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyUpdateProfileSuccess {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
    }

    @Test
    public void VerifyUpdateProfileSuccess() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Login function");
        loginPage.loginFunction();
        sideBar.openHomePage();
        profilePage.clickLinkProfile();
        profilePage.waitForProfilePageAppear();
        profilePage.deleteAllTextBox();

        Allure.step("Update profile with proper values");
        profilePage.updateProfile("Thy", "due", "0845123456");

        Allure.step("Check update success");
        softAssert.assertEquals(profilePage.textMessageUpdateSuccess(), "Edit success!", "Error update");
        softAssert.assertEquals(sideBar.headerNameAccount(), "Thy", "Update error");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ProfilePage profilePage;
    LoginPage loginPage;
    SideBar sideBar;
    ConfigReader configReader;
    SoftAssert softAssert;
}
