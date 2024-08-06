package example.LeadManagement_AddNewLead;

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

public class VerifySuccessfulAddACustomerWhenUserInputValidDataForAllFields {
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
    public void testSuccess() {

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

        Allure.step("Click [Create a customer] button");
        createCustomerPage.clickCreateACustomerButton();
//        createCustomerPage.waitForCreateCustomerPageIsDisappear();

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        //xác minh Customer vừa được thêm vào hiển thị ở đầu danh sách customer
        softAssert.assertEquals(showAllCustomersPage.nameOfCustomerAtTheTopOfTheList(), name, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.emailOfCustomerAtTheTopOfTheList(), email, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.phoneOfCustomerAtTheTopOfTheList(), phone, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.addressOfCustomerAtTheTopOfTheList(), address, "The newly created customer is not at the top of the list.");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
