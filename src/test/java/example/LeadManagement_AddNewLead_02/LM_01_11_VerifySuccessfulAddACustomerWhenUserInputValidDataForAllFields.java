package example.LeadManagement_AddNewLead_02;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import models.CustomerInFormationForm;
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
import page.SideBar;
import utils.ConfigReader;

import java.time.Duration;

public class LM_01_11_VerifySuccessfulAddACustomerWhenUserInputValidDataForAllFields {
    WebDriver driver;
    ConfigReader configReader;
    LoginPage loginPage;
    ShowAllCustomersPage showAllCustomersPage;
    CreateCustomerPage createCustomerPage;
    Faker faker;
    SoftAssert softAssert;
    CustomerInFormationForm customerInfor;
    SideBar sideBar;

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
        sideBar = new SideBar(driver);

        name = faker.name().name();
        email = faker.internet().emailAddress();
        phone = RandomStringUtils.randomNumeric(10);
        address = faker.address().fullAddress();
        customerInfor = new CustomerInFormationForm(name,email, phone, address);
    }

    @Test
    public void testLM_01_11() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
//        softAssert.assertTrue(loginPage.isDisappearLoginForm(), "[Login] page is still display");

        Allure.step("Open [Create Customer] page");
        sideBar.openCreateCustomerPage();

        softAssert.assertTrue(createCustomerPage.isCreateCustomerPageDisplayed(), "[Create customer] page is not displayed");

        Allure.step("Input valid data for all fields");
        createCustomerPage.createCustomerInformation(customerInfor);

        softAssert.assertTrue(showAllCustomersPage.isShowAllCustomerPageDisplayed(), "[Show all customers] page is not displayed");

        //xác minh Customer vừa được thêm vào hiển thị ở đầu danh sách customer
        softAssert.assertEquals(showAllCustomersPage.getCustomerNameByIndex(1), name, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.getCustomerEmailByIndex(1), email, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.getCustomerPhoneByIndex(1), phone, "The newly created customer is not at the top of the list.");
        softAssert.assertEquals(showAllCustomersPage.getCustomerAddressByIndex(1), address, "The newly created customer is not at the top of the list.");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
