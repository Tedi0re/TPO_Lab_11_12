package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {
    private static final String RESOURCE_PATH = "src\\test\\resources\\";
    private static WebDriver driver;

    private DriverSingleton(){}

    public  static WebDriver getDriver(){
        if(driver == null){
            try {
                switch (System.getProperty("browser")){
                    case "chrome":{
                        driver = new ChromeDriver();
                    }
                    case "edge":{
                        driver = new EdgeDriver();
                    }
                    default:{
                        driver = new ChromeDriver();
                    }
                }
            } catch (NullPointerException exception){
                //driver = new EdgeDriver();
                driver = new ChromeDriver();
            }

        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
