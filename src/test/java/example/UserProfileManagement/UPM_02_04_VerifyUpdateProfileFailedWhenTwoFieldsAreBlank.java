package example.UserProfileManagement;

import example.TestBase;
import io.qameta.allure.Allure;
import models.Objects.User;

import example.TestBase;
import io.qameta.allure.Allure;
import models.Objects.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.ProfilePage.ProfilePage;

public class UPM_02_04_VerifyUpdateProfileFailedWhenTwoFieldsAreBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        user1 = new User("", "", "0913256561");
        user2 = new User("", "iviettech", "");
        user3 = new User("Vy", "", "");
    }

    @Test
    public void VerifyUpdateProfileFailedWhenTwoFieldsAreBlank() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());
        
        Allure.step("Progress open profile page");
        profilePage.progressOpenProfile();

        Allure.step("Update profile with no Name and Company");
        profilePage.updateProfile(user1);

        //Hai truong Name va Company
        Allure.step("Check message at Name and Company fields");
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");

        //Hai truong Name va Phone
        profilePage.deleteAllTextBox();
        Allure.step("Update profile with no Name and Phone");
        profilePage.updateProfile(user2);

        Allure.step("Check message at Name and Phone fields");
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");
        softAssert.assertEquals(profilePage.phoneTextMessage(), "Please enter your phone", "Error phone field");

        //Hai truong Phone va Company
        profilePage.deleteTxbCompany();
        Allure.step("Update profile with no Company and Phone");
        profilePage.updateProfile(user3);

        Allure.step("Check message at Company and Phone fields");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");
        softAssert.assertEquals(profilePage.phoneTextMessage(), "Please enter your phone", "Error phone field");

        softAssert.assertAll();
    }


    ProfilePage profilePage;
    User user1;
    User user2;
    User user3;
    SoftAssert softAssert;
}
