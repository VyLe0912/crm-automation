package UpdateProfileCases;

import ProfilePage.ProfilePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyUpdateProfileSuccess {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void VerifyUpdateProfileSuccess() {
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1378, 744));
        loginPage.loginFunction();
        homePage.openHomePage();
        profilePage.clickLinkProfile();
        profilePage.waitForProfilePageAppear();
        profilePage.deleteAllTextBox();
        profilePage.updateProfile("Thy", "due", "0845123456");

        softAssert.assertEquals(profilePage.textMessageUpdateSuccess(), "Edit success!", "Error update");
        softAssert.assertEquals(homePage.headerNameAccount(), "Thy", "Update error");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ProfilePage profilePage;
    LoginPage loginPage;
    HomePage homePage;
    ConfigReader configReader;
    SoftAssert softAssert;
}
