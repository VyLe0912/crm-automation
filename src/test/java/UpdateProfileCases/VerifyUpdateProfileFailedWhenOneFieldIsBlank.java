package UpdateProfileCases;

import ProfilePage.ProfilePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyUpdateProfileFailedWhenOneFieldIsBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void VerifyUpdateProfileFailedWhenOneFieldIsBlank() {
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1378, 744));
        profilePage.progressOpenProfile();
        profilePage.updateProfile("", "iviettech", "0913256561");

        //Truong Name
        softAssert.assertEquals(profilePage.nameTextMessage(), "Please enter your name", "Error name field");

        profilePage.deleteAllTextBox();
        profilePage.updateProfile("Vy", "", "0913256561");
        //Truong Company
        softAssert.assertEquals(profilePage.companyTextMessage(), "Please enter your company", "Error company field");

        profilePage.deleteAllTextBox();
        profilePage.updateProfile("Vy", "iviettech", "");
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
