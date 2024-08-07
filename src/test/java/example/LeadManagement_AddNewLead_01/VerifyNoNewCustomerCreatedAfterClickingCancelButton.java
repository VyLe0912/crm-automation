package example.LeadManagement_AddNewLead_01;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
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

public class VerifyNoNewCustomerCreatedAfterClickingCancelButton {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;

    String name;
    String email;
    String phone;
    String address;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        configReader = new ConfigReader();
        loginPage = new LoginPage(driver);
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        createCustomerPage = new CreateCustomerPage(driver);
        faker = new Faker();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        softAssert = new SoftAssert();

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
    }

    @Test
    public void testLM_01_10() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForShowAllCustomersPageIsDisplayed();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");
        Allure.step("Click [New customer] button");
        showAllCustomersPage.clickNewCustomerButton();
        showAllCustomersPage.waitForCreateCustomerPageIsDisplayed();

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "[Create customer] page is not displayed");
        Allure.step("Input valid data for all fields");
        createCustomerPage.createCustomer(name, email, phone, address);

        Allure.step("Click [Cancel] button");
        createCustomerPage.clickCancelButton();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed());

        Allure.step("Search with name of customer just created");
        showAllCustomersPage.inputCustomerNameToSearch(name);

        softAssert.assertEquals(showAllCustomersPage.messageWhenSearchNameDoesNotExist(), "No records found.", "Success create customer when clicking [Cancel] button");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
