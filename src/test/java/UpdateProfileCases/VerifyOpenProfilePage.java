package UpdateProfileCases;

import example.TestBase;
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
import utils.ConfigReader;

import java.time.Duration;

public class VerifyOpenProfilePage extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        sideBar = new SideBar(driver);
    }

    @Test
    public void VerifyOpenProfilePage() {

        Allure.step("Login function");
        loginPage.loginFunction();
        sideBar.openHomePage();

        Allure.step("Open profile page");
        profilePage.clickLinkProfile();
        profilePage.waitForProfilePageAppear();
        softAssert.assertEquals(profilePage.labelProfilePage(), "User Information", "Can't open profile page");
        softAssert.assertAll();
    }

    ProfilePage profilePage;
    LoginPage loginPage;
    SideBar sideBar;
    SoftAssert softAssert;
}
