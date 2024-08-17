package example.RegisterCases;

import example.TestBase;
import io.qameta.allure.Allure;
import models.RegisterForm;
import models.RegisterUser;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class VerifyRegisterFailedWhenOneFieldIsBlank extends TestBase {
    @BeforeMethod
    public void setUp() {
        super.setUp();
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

        //De trong truong Email
        Allure.step("Check register function with no Email");
        registerUser = new RegisterUser("", password, confPassword, name, company,phone);
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Email field");
        softAssert.assertEquals(registerForm.textMessEmail(), "Please enter your email", "Error email field");

        Allure.step("Delete all text box");
        registerForm.deleteAllTextbox();

        //De trong truong password
        Allure.step("Check register function with no Password");
        registerUser = new RegisterUser(email, "", confPassword, name, company, phone);
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Password field");
        softAssert.assertEquals(registerForm.textMessPass(), "Please enter your password", "Error password field");

        Allure.step("Delete all text box");
        registerForm.deleteAllTextbox();

        //De trong truong confirm password
        Allure.step("Check register function with no Confirm Password");
        registerUser = new RegisterUser(email, password, "", name, company, phone);
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Confirm Password field");
        softAssert.assertEquals(registerForm.textMessConfPass(), "Please confirm your password", "Error confirm password field");

        Allure.step("Delete all text box");
        registerForm.deleteAllTextbox();

        //De trong truong name
        Allure.step("Check register function with no Name");
        registerUser = new RegisterUser(email, password, confPassword, "", company, phone);
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Name field");
        softAssert.assertEquals(registerForm.textMessName(), "Please enter your name", "Error name field");

        Allure.step("Delete all text box");
        registerForm.deleteAllTextbox();

        //De trong truong company
        Allure.step("Check register function with no Company");
        registerUser = new RegisterUser(email, password, confPassword, name, "", phone);
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Company field");
        softAssert.assertEquals(registerForm.textMessCompany(), "Please enter your company", "Error company field");

        Allure.step("Delete all text box");
        registerForm.deleteAllTextbox();

        //De trong truong phone
        Allure.step("Check register function with no Phone");
        registerUser = new RegisterUser(email, password, confPassword, name, company, "");
        registerForm.SignUp(registerUser);

        Allure.step("Check message at Phone field");
        softAssert.assertEquals(registerForm.textMessPhone(), "Please enter your phone", "Error phone field");

        softAssert.assertAll();
    }


    RegisterForm registerForm;
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
