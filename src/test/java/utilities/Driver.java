package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {

    }

    static private WebDriver driver;

    public static WebDriver getDriver() {


        if(driver==null) {

            switch(ConfigReader.getProperty("browser")) {

                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("no-sandbox");
            
    
        options.addArguments("--window-size=1920,1080"); 
        options.addArguments("--disable-infobars"); 
        options.addArguments("--disable-extensions"); 
        options.addArguments("--disable-gpu"); 
        options.addArguments("--disable-dev-shm-usage"); 
                    
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "Opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                case "Safari":
                    WebDriverManager.getInstance(SafariDriver.class);
                    driver =new SafariDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
            }

        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;

    }

    public static void closeDriver() {
        if (driver!=null) {
            driver.close();
            driver=null;
        }
    }
}
