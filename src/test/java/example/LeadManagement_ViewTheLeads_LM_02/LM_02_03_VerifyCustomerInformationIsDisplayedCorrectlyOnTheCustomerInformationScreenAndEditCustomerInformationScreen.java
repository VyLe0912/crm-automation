package example.LeadManagement_ViewTheLeads_LM_02;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.CustomerInformationPage;
import page.EditCustomerInformationPage;
import page.LoginPage;
import page.ShowAllCustomersPage;
import utils.ConfigReader;

import java.time.Duration;
import java.util.Random;

public class LM_02_03_VerifyCustomerInformationIsDisplayedCorrectlyOnTheCustomerInformationScreenAndEditCustomerInformationScreen {
    WebDriver driver;
    ConfigReader configReader;
    ShowAllCustomersPage showAllCustomersPage;
    SoftAssert softAssert;
    LoginPage loginPage;
    Random random;
    CustomerInformationPage customerInformationPage;
    EditCustomerInformationPage editCustomerInformationPage;

    String name;
    String email;
    String phone;
    String address;
    int randomNumber;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        showAllCustomersPage = new ShowAllCustomersPage(driver);
        loginPage = new LoginPage(driver);
        random = new Random();
        customerInformationPage = new CustomerInformationPage(driver);
        editCustomerInformationPage = new EditCustomerInformationPage(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        randomNumber = random.nextInt(10);


    }

    @Test
    public void testLM_02_03() {

        Allure.step("Open CRM website");
        driver.get(configReader.getUrl());

        Allure.step("Login success");
        loginPage.login("abcTrang@gmail.com", "123123");
        showAllCustomersPage.waitForNewCustomerButtonIsDisplayed();

        // bam chon 1 customer bat ki
        name = showAllCustomersPage.getCustomerNameByIndex(randomNumber);
        email = showAllCustomersPage.getCustomerEmailByIndex(randomNumber);
        phone = showAllCustomersPage.getCustomerPhoneByIndex(randomNumber);
        address = showAllCustomersPage.getCustomerAddressByIndex(randomNumber);
        showAllCustomersPage.openCustomerInformationPage(randomNumber);

        // xac minh chuyen den man hinh customer information
        softAssert.assertTrue(customerInformationPage.isCustomerInformationPageDisplayed());

        // xac minh thong tin customer o man hinh customer information
        softAssert.assertEquals(customerInformationPage.getCustomerName(), name, "Name is incorrect");
        softAssert.assertEquals(customerInformationPage.getCustomerEmail(), email, "Email is incorrect");
        softAssert.assertEquals(customerInformationPage.getCustomerPhone(), phone, "Phone is incorrect");
        softAssert.assertEquals(customerInformationPage.getCustomerAddress(), address, "Address is incorrect");

        // bam chon edit
        customerInformationPage.clickEditButton();

        // xac minh chuyen den man hinh edit customer information
        softAssert.assertTrue(editCustomerInformationPage.isEditCustomerInformationDisplayed());

        // xac minh thong tin customer o man hinh edit customer information
        softAssert.assertEquals(editCustomerInformationPage.getCustomerName(), name, "Name is incorrect");
        softAssert.assertEquals(editCustomerInformationPage.getCustomerEmail(), email, "Email is incorrect");
        softAssert.assertEquals(editCustomerInformationPage.getCustomerPhone(), phone, "Phone is incorrect");
        softAssert.assertEquals(editCustomerInformationPage.getCustomerAddress(), address, "Address is incorrect");

        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }
}
