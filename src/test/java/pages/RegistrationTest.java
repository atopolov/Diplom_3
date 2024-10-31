package pages;

import api.User;
import com.github.javafaker.Faker;
import config.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import rule.DriverRule;

import static org.junit.Assert.*;

// Test for registration page
public class RegistrationTest {
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private User userForCreate;
    private User userForLogin;
    private UserHelper userHelper;
    private String incorrectPassword;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        registrationPage = new RegistrationPage(driver);
        registrationPage.openRegistrationPage();
        loginPage = new LoginPage(driver);

        Faker faker = new Faker();
        userForCreate = new User(faker.internet().emailAddress(), faker.internet().password(6, 12), faker.name().fullName());
        userForLogin = new User(userForCreate.getEmail(), userForCreate.getPassword());
        incorrectPassword = faker.internet().password(1, 5);
        userHelper = new UserHelper();
    }

    @Test
    @DisplayName("Register user happy path")
    public void registrationTest() {
        registrationPage.registration(userForCreate.getName(), userForCreate.getEmail(), userForCreate.getPassword());
        loginPage.waitEntranceDisplayed();

        String expected = "Вход";
        String actual = loginPage.getEntranceText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Register with incorrect password")
    public void registrationNegative() {
        registrationPage.registration(userForCreate.getName(), userForCreate.getEmail(), incorrectPassword);

        String expected = "Некорректный пароль";
        String actual = registrationPage.getErrorMessage();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        userHelper.setUserLogin(userForLogin);
        userHelper.deleteUser();
    }
}