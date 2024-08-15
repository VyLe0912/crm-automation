package UpdateProfileCases;

import example.TestBase;
import io.qameta.allure.Allure;
import page.Objects.User;
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
import utils.ConfigReader;

import java.time.Duration;

public class VerifyUpdateProfileSuccess extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
        user = new User("Vy", "due", "0845123456");
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
        profilePage.updateProfile(user);

        Allure.step("Check update success");
        softAssert.assertEquals(profilePage.textMessageUpdateSuccess(), "Edit success!", "Error update");
        softAssert.assertEquals(sideBar.headerNameAccount(), "Vy", "Update error");
        softAssert.assertAll();
    }

    User user;
    ProfilePage profilePage;
    LoginPage loginPage;
    SideBar sideBar;
    SoftAssert softAssert;
}
