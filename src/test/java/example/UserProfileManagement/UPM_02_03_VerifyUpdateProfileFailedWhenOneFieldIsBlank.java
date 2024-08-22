package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import models.Objects.User;
import page.ProfilePage.ProfilePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UPM_02_03_VerifyUpdateProfileFailedWhenOneFieldIsBlank extends TestBase {

    @BeforeMethod
    public void setUp() {
        super.setUp();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        user1 = new User("","iviettech", "0913256561" );
        user2 = new User("Vy", "", "0913256561");
        user3 = new User("Vy", "iviettech", "" );
    }

    @Test
    public void VerifyUpdateProfileFailedWhenOneFieldIsBlank() {

        Allure.step("Progress open profile page");
        profilePage.progressOpenProfile();

        Allure.step("Update profile with no Name");
        profilePage.updateProfile(user1);

        //Truong Name
        Allure.step("Check message at Name field");
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");

        profilePage.deleteAllTextBox();

        Allure.step("Update profile with no Company");
        profilePage.updateProfile(user2);

        //Truong Company
        Allure.step("Check message at Company field");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");

        profilePage.deleteAllTextBox();
        Allure.step("Update profile with no Phone");
        profilePage.updateProfile(user3);

        //Truong Phone
        Allure.step("Check message at Phone field");
        softAssert.assertEquals(profilePage.phoneTextMessage(), "Please enter your phone", "Error phone field");
        softAssert.assertAll();
    }

    ProfilePage profilePage;
    User user1;
    User user2;
    User user3;
    SoftAssert softAssert;
}
