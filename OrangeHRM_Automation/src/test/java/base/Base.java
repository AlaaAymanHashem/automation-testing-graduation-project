package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.DashBoardPage;
import pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class Base {

    public WebDriver driver;
    public LoginPage loginPage;
    public DashBoardPage dashBoardPage;

    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver(getChromeOptions());
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
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

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
