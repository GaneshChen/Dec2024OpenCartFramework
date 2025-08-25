/**
 * BeforeTest --> It runs Before all the @Test Annotations.
 * AfterTest --> It runs After all the @Test Annotations.
 */

package com.qa.opencart.base;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

//@Listeners(ChainTestListener.class)
public class BaseTest {
 WebDriver driver;
 DriverFactory df;
 protected Properties prop;
 protected LoginPage loginPage;
 protected AccountsPage accPage;

 protected ProductInfoPage productInfoPage;

 protected SearchResultsPage searchResultsPage;

 protected RegisterPage registerPage;

    @Parameters({"browser"})
    @BeforeTest
    public void setUp(String browserName){
        df = new DriverFactory();
        prop=df.initProp();

        if(browserName!=null){
            prop.setProperty("browser",browserName);
        }
        ChainTestListener.log("Properties Used :" + prop);
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);
    }
    @AfterMethod
    public void attachScreenshot(ITestResult result){
//        if(!result.isSuccess()){
//            ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png");
//        }
        ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
