package UpdateProfileCases;

import page.ProfilePage.ProfilePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.HomePage;
import page.LoginPage;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyOpenProfilePage {
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
    public void VerifyOpenProfilePage() {
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1378, 744));
        loginPage.loginFunction();
        homePage.openHomePage();
        profilePage.clickLinkProfile();
        profilePage.waitForProfilePageAppear();
        softAssert.assertEquals(profilePage.labelProfilePage(), "User Information", "Can't open profile page");
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
