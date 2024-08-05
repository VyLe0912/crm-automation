package LoginTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import utils.ConfigReader;

public class VerifyLoginFailedWhenInsertInvalidValue {
    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void VerifyLoginFailedWhenInsertInvalidValue() {
    }


    @AfterMethod
    public void cleanUp() {
    }

    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    SoftAssert softAssert;
}
