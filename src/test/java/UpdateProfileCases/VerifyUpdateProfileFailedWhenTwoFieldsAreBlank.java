package UpdateProfileCases;

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

public class VerifyUpdateProfileFailedWhenTwoFieldsAreBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
    }

    @Test
    public void VerifyUpdateProfileFailedWhenTwoFieldsAreBlank() {
        driver.get(configReader.getUrl());
        profilePage.progressOpenProfile();

        profilePage.updateProfile("", "", "0913256561");

        //Hai truong Name va Company
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");

        //Hai truong Name va Phone
        profilePage.deleteAllTextBox();
        profilePage.updateProfile("", "iviettech", "");
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");
        softAssert.assertEquals(profilePage.phoneTextMessage(), "Please enter your phone", "Error phone field");

        //Hai truong Phone va Company
        profilePage.deleteTxbCompany();
        profilePage.updateProfile("Vy", "", "");
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");
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
