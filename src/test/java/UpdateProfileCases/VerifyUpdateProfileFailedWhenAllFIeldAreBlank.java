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

public class VerifyUpdateProfileFailedWhenAllFIeldAreBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyUpdateProfileFailedWhenAllFIeldAreBlank() {
        Allure.step("Open CRM Website");
        driver.get(configReader.getUrl());

        Allure.step("Progress open profile page");
        profilePage.progressOpenProfile();
        profilePage.clickBtnSaveProfile();

        //Truong Name
        Allure.step("Check message at Name field");
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");

        //Truong Company
        Allure.step("Check message at Company field");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");

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
