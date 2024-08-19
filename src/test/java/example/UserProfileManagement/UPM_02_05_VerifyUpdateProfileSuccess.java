package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import models.Objects.User;

import example.TestBase;
import io.qameta.allure.Allure;
import models.Objects.User;
import page.ProfilePage.ProfilePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.Login.LoginPage;
import page.SideBar.SideBar;

public class UPM_02_05_VerifyUpdateProfileSuccess extends TestBase {
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
        loginPage.loginWithDefaultAccount();
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
