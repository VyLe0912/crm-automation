package example.LeadManagement_AddNewLead_01;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CreateCustomerPage;
import page.LoginPage;
import page.ShowAllCustomersPage;
import utils.ConfigReader;

import java.time.Duration;

public class VerifyCannotSuccessCreateACustomerWhenLeaveBlankAllFields {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testLM_01_02() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "Create success");

        // kiem tra thong bao hien thi o truong [Name]
        softAssert.assertEquals(createCustomerPage.getErrorForNameField(), "Please enter your name", "No message in name field");

        // kiem tra thong bao hien thi o truong [Email]
        softAssert.assertEquals(createCustomerPage.getErrorForEmailField(), "Please enter your email", "No message in email field");

        // kiem tra thong bao hien thi o truong [Phone]
        softAssert.assertEquals(createCustomerPage.getErrorForPhoneField(), "Please enter your phone", "No message in phone field");

        // kiem tra thong bao hien thi o truong [Address]
        softAssert.assertEquals(createCustomerPage.getErrorForAddressField(), "Please enter your address", "No message in address field");

        softAssert.assertAll();

    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
