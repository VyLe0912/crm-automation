package RegisterCases;

import page.Register.RegisterForm;
import page.Register.RegisterUser;
import com.github.javafaker.Faker;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.utils.ConfigReader;

import java.time.Duration;

public class VerifyRegisterFailedWhenOneFieldIsBlank {
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        configReader = new ConfigReader();
        softAssert = new SoftAssert();
        registerForm = new RegisterForm(driver);
        faker = new Faker();
        email = faker.internet().emailAddress();
        password = faker.internet().password();
        confPassword = faker.internet().password();
        name = faker.funnyName().name();
        company = faker.address().buildingNumber();
        phone = faker.phoneNumber().phoneNumber();
    }

    @Test
    public void verifyRegisterFailedWhenOneFieldIsBlank() {
        driver.get(configReader.getUrl());

        //De trong truong Email
        registerUser = new RegisterUser("", password, confPassword, name, company,phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessEmail(), "Please enter your email", "Error email field");

        //De trong truong password
        registerForm.deleteAllTextbox();
        registerUser = new RegisterUser(email, "", confPassword, name, company, phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessPass(), "Please enter your password", "Error password field");

        //De trong truong confirm password
        registerForm.deleteAllTextbox();
        registerUser = new RegisterUser(email, password, "", name, company, phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessConfPass(), "Please confirm your password", "Error confirm password field");

        //De trong truong name
        registerForm.deleteAllTextbox();
        registerUser = new RegisterUser(email, password, confPassword, "", company, phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessName(), "Please enter your name", "Error name field");

        //De trong truong company
        registerForm.deleteAllTextbox();
        registerUser = new RegisterUser(email, password, confPassword, name, "", phone);
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessCompany(), "Please enter your company", "Error company field");

        //De trong truong phone
        registerForm.deleteAllTextbox();
        registerUser = new RegisterUser(email, password, confPassword, name, company, "");
        registerForm.SignUp(registerUser);
        softAssert.assertEquals(registerForm.textMessPhone(), "Please enter your phone", "Error phone field");

        softAssert.assertAll();
    }


    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        driver.quit();
    }

    WebDriver driver;
    RegisterForm registerForm;
    ConfigReader configReader;
    SoftAssert softAssert;
    RegisterUser registerUser;
    Faker faker;
    String email;
    String password;
    String confPassword;
    String name;
    String company;
    String phone;
}
