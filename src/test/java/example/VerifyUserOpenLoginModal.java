package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfigReader;

public class VerifyUserOpenLoginModal {
    By labelLoginSelector = By.xpath("(//div[@class = 'ibox-title'])[1]");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void verifyLoginFunction() {
        driver.get(configReader.getUrl());
        String labelLogin = driver.findElement(labelLoginSelector).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(labelLogin, "Login", "Access web failed");
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
}
