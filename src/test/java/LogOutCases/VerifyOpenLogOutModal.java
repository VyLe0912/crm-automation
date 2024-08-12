package LogOutCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;
import page.utils.ConfigReader;

public class VerifyOpenLogOutModal {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void VerifyOpenLogOutModal() {
        driver.get(configReader.getUrl());
        loginPage.loginFunction();
        homePage.clickBtnLogOut();

    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    ConfigReader configReader;
}
