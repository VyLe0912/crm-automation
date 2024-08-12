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
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyUpdateProfileFailedWhenOneFieldIsBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyUpdateProfileFailedWhenOneFieldIsBlank() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Progress open profile page");
        profilePage.progressOpenProfile();

        Allure.step("Update profile with no Name");
        profilePage.updateProfile("", "iviettech", "0913256561");

        //Truong Name
        Allure.step("Check message at Name field");
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");

        profilePage.deleteAllTextBox();

        Allure.step("Update profile with no Company");
        profilePage.updateProfile("Vy", "", "0913256561");

        //Truong Company
        Allure.step("Check message at Company field");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");

        profilePage.deleteAllTextBox();
        Allure.step("Update profile with no Phone");
        profilePage.updateProfile("Vy", "iviettech", "");

        //Truong Phone
        Allure.step("Check message at Phone field");
        softAssert.assertEquals(profilePage.phoneTextMessage(), "Please enter your phone", "Error phone field");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ProfilePage profilePage;
    ConfigReader configReader;
    SoftAssert softAssert;
}
