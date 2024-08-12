package example;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyLoginFunctionSuccess {
    By headerLoginSelector = By.xpath("(//strong[@class='font-bold'])[2]");
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyLoginFunction() {
        driver.get(configReader.getUrl());
        driver.manage().window().setSize(new Dimension(1378, 744));
        loginPage.login("abc@gmail.com", "VyLe123!");
        String headerLogin = driver.findElement(headerLoginSelector).getText();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(headerLogin, "Vy", "Login failed");
        softAssert.assertAll();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
}
