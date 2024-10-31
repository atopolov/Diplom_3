package rule;

import config.Config;
import config.ConfigUser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

// Rule for driver initialization
@Getter
public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before(){
        initDriver();
    }

    @Override
    protected void after(){
        driver.quit();
    }

    // Method for driver initialization
    public void initDriver(){
        if("firefox".equals(System.getProperty("browser"))){
            startFirefoxBrowser();
        } else {
            startChromeBrowser();
        }
    }

    // Method for start chrome browser
    private void startChromeBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
    }

    // Method for start firefox browser
    public void startFirefoxBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
    }
}