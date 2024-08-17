<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_02_01_VerifyOpenProfilePage.java
package example.UserProfileManagement;
========
package example.UpdateProfileCases;
>>>>>>>> 54ace25 (lm04):src/test/java/example/UpdateProfileCases/VerifyOpenProfilePage.java

import example.TestBase;
import io.qameta.allure.Allure;
import page.ProfilePage.ProfilePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
<<<<<<<< HEAD:src/test/java/example/UserProfileManagement/UPM_02_01_VerifyOpenProfilePage.java
import page.LoginPage;
import page.SideBar;

public class UPM_02_01_VerifyOpenProfilePage extends TestBase {
========
import page.Login.LoginPage;
import page.SideBar.SideBar;

public class VerifyOpenProfilePage extends TestBase {
>>>>>>>> 54ace25 (lm04):src/test/java/example/UpdateProfileCases/VerifyOpenProfilePage.java
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
        loginPage.loginWithDefaultAccount();
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
