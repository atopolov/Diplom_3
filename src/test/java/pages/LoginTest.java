package pages;

import api.User;
import com.github.javafaker.Faker;
import config.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import rule.DriverRule;

import static org.junit.Assert.*;

// Test for login page and authorization
public class LoginTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private User userForLogin;
    private UserHelper userHelper;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

        Faker faker = new Faker();
        User userForCreate = new User(faker.internet().emailAddress(), faker.internet().password(6, 12), faker.name().fullName());
        userForLogin = new User(userForCreate.getEmail(), userForCreate.getPassword());
        userHelper = new UserHelper();
        userHelper.setUser(userForCreate);
        userHelper.createUser();
    }

    @Test
    @DisplayName("Login user via loginAccountButton")
    public void loginUserViaLoginAccountButton() {
        mainPage.openMainPage();
        mainPage.clickLoginAccountButton();
        loginPage.loginUser(userForLogin.getEmail(), userForLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Login user via accountProfileButton")
    public void loginUserViaAccountProfileButton() {
        mainPage.openMainPage();
        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userForLogin.getEmail(), userForLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Login user via RegistrationPage")
    public void loginUserViaRegistrationPage() {
        registrationPage.openRegistrationPage();
        registrationPage.clickLoginButton();
        loginPage.loginUser(userForLogin.getEmail(), userForLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Login user via ForgotPasswordPage")
    public void loginViaForgotPasswordPage() {
        forgotPasswordPage.openForgotPasswordPage();
        forgotPasswordPage.clickLoginButton();
        loginPage.loginUser(userForLogin.getEmail(), userForLogin.getPassword());
        mainPage.waitCreateOrderButton();

        String expected = "Оформить заказ";
        String actual = mainPage.getCreateOrderButtonText();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        userHelper.setUserLogin(userForLogin);
        userHelper.deleteUser();
    }
}