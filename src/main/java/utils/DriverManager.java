package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Locale;

public class DriverManager {

    public DriverManager(){
        throw new UnsupportedOperationException("You can not create object of this class!");
    }

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver getDriver(){
        WebDriver driver;
        String browser = ConfigManager.getProperty("browser");
        if(THREAD_LOCAL.get() == null){
            switch (browser.toLowerCase(Locale.ROOT)){
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--ignore-certificate-error");
                    driver = new EdgeDriver(edgeOptions);break;

                case "firefox" :
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--height-1080");
                    firefoxOptions.addArguments("--width-1920");
                    driver = new FirefoxDriver(firefoxOptions);break;

                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--ignore-certificate-error");
                    driver = new ChromeDriver(chromeOptions);
            }
            driver.get(ConfigManager.getProperty("baseURL"));
            THREAD_LOCAL.set(driver);
        }

        return THREAD_LOCAL.get();
    }

    public static void closeDriver(){
        WebDriver driver = THREAD_LOCAL.get();
        if (driver != null){
            driver.quit();
            THREAD_LOCAL.remove();
        }
    }
}
