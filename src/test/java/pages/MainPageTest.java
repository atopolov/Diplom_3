package pages;

import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.*;
import rule.DriverRule;

import static org.junit.Assert.*;

// Test for main page
public class MainPageTest {
    private MainPage mainPage;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        WebDriver driver = driverRule.getDriver();
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Bun tab should be selected")
    public void bunTabShouldBeSelected() {
        mainPage.clickSauceTab();
        mainPage.clickBunTab();
        assertTrue(mainPage.isBunTabSelected());
    }

    @Test
    @DisplayName("Sauce tab should be selected")
    public void sauceTabShouldBeSelected() {
        mainPage.clickSauceTab();
        assertTrue(mainPage.isSauceTabSelected());
    }

    @Test
    @DisplayName("Filling tab should be selected")
    public void fillingTabShouldBeSelected() {
        mainPage.clickFillingTab();
        assertTrue(mainPage.isFillingTabSelected());
    }
}