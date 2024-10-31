package pages;

import api.User;
import com.github.javafaker.Faker;
import config.UserHelper;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import rule.DriverRule;

import static org.junit.Assert.*;

// Test for account profile page
public class AccountProfileTest {
    private AccountProfilePage accountProfilePage;
    private MainPage mainPage;
    private LoginPage loginPage;
    private User userForLogin;
    private UserHelper userHelper;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        accountProfilePage = new AccountProfilePage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        mainPage.openMainPage();

        Faker faker = new Faker();
        User userForCreate = new User(faker.internet().emailAddress(), faker.internet().password(6, 12), faker.name().fullName());
        userForLogin = new User(userForCreate.getEmail(), userForCreate.getPassword());
        userHelper = new UserHelper();
        userHelper.setUser(userForCreate);
        userHelper.createUser();

        mainPage.clickAccountProfileButton();
        loginPage.loginUser(userForLogin.getEmail(), userForLogin.getPassword());
        mainPage.clickAccountProfileButton();
    }

    @Test
    @DisplayName("Check account profile for authorized user")
    public void checkAccountProfile() {
        accountProfilePage.waitProfileTab();

        String expected = "Профиль";
        String actual = accountProfilePage.getProfileTabText();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Open constructor from account profile")
    public void openConstructorFromProfile() {
        accountProfilePage.clickConstructorLink();

        String expected = "Соберите бургер";
        String actual = mainPage.getMainTextConstructor();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Open main page from account profile")
    public void openMainPageFromAccount() {
        accountProfilePage.clickLogoLink();

        String expected = "Соберите бургер";
        String actual = mainPage.getMainTextConstructor();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Logout from account profile")
    public void logoutFromAccount() {
        accountProfilePage.waitLogoutButton();
        accountProfilePage.clickLogoutButton();
        loginPage.waitEntranceDisplayed();

        String expected = "Вход";
        String actual = loginPage.getEntranceText();

        assertEquals(expected, actual);
    }

    @After
    public void tearDown() {
        userHelper.setUserLogin(userForLogin);
        userHelper.deleteUser();
    }
}