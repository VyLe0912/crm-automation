package LoginTestCases;

import io.qameta.allure.Allure;
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
        softAssert = new SoftAssert();
    }

    @Test
    public void verifyLoginFunction() {
        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());
        String labelLogin = driver.findElement(labelLoginSelector).getText();
        SoftAssert softAssert = new SoftAssert();

        //Kiểm tra hiển thị trang Đăng Nhập
        softAssert.assertEquals(labelLogin, "Login", "Access web failed");
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    SoftAssert softAssert;
}
