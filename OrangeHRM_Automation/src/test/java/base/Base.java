package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class Base {

    public WebDriver driver;
    public LoginPage loginPage;
    public DashBoardPage dashBoardPage;
    public PIMPage pimPage;

    @BeforeMethod
    public void setUp(){
        String browser = ConfigReader.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(getChromeOptions());
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getBaseUrl() + "auth/login");

        loginPage = new LoginPage(driver);
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck");
        return options;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
