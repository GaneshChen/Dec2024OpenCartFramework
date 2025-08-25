package com.qa.opencart.factory;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /****
     * This Method is used to Initialize the driver on the basis of given Browser Name
     * @param prop
     * @return
     */

    public WebDriver initDriver(Properties prop){
        ChainTestListener.log("Properties Used :" +prop);
        String browserName = prop.getProperty("browser");
        System.out.println("Browser Name:"+browserName);
        optionsManager = new OptionsManager(prop);
        switch (browserName.toLowerCase().trim()){
            case "chrome":
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;
            default:
                System.out.println("====Please pass the Valid Browser Name"+browserName);
                throw new BrowserException("===INVALID BROWSER===");

        }
        getDriver().get(prop.getProperty("url"));
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();
    }
    /**
     * getDriver : get the Local Thread Copy of the Driver
     */

    public static WebDriver getDriver(){
        return tlDriver.get();
    }

    public static File getScreenshotFile(){
     File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
     return srcFile;
    }

    public static String getScreenshotBase64(){
        return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
    }

    public static byte[] getScreenshotByte(){
        return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
    }


    /**
     * This is used to initialize the Config Properties.
     * @return
     */
    //mvn
    //mvn clean install -Denv="qa"
    public Properties initProp()  {
        String envName = System.getProperty("env");
        System.out.println("Running tests on: "+envName);
        FileInputStream ip = null;
        prop = new Properties();
        try{

            if(envName==null){
            System.out.println("env is null, hence running the tests in QA env");

                ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
            }
        else {
            System.out.println("Running test on env: "+envName);
            switch (envName.toLowerCase().trim()){
                case "qa":
                    ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                    break;
                case "stage":
                    ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                    break;
                case "prod":
                    ip = new FileInputStream("./src/test/resources/config/config.properties");
                    break;
                default:
                    throw new FrameworkException("===INVALID ENV NAME=== :" + envName);
                }
        }
    } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
