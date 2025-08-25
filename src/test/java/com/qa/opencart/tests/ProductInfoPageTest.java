package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {

    @BeforeClass
    public void productInfoSetUp(){
       accPage =loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProductTestData(){
        return new Object[][]{
                {"macbook","MacBook Pro"},
                {"macbook","MacBook Air"},
                {"imac","iMac"},
                {"samsung","Samsung SyncMaster 941BW"},
                {"samsung","Samsung Galaxy Tab 10.1"}
        };
    }

    @Test(dataProvider = "getProductTestData")
    public void productHeaderTest(String searchKey, String productName){
     searchResultsPage =  accPage.doSearch(searchKey);
     productInfoPage = searchResultsPage.selectProduct(productName);
     String actHeader = productInfoPage.getProductHeader();
     Assert.assertEquals(actHeader,productName);
    }

    @DataProvider
    public Object[][] getProductImagesTestData(){
        return new Object[][]{
                {"macbook","MacBook Pro",4},
                {"macbook","MacBook Air",4},
                {"imac","iMac",3},
                {"samsung","Samsung SyncMaster 941BW",1},
                {"samsung","Samsung Galaxy Tab 10.1",7}
        };
    }
    @DataProvider
    public Object[][] getProductCSVData(){
        return CSVUtil.csvData("product");
    }

    @Test (dataProvider = "getProductImagesTestData")
    public void productImageCountTest(String searchKey, String productName, int imageCount ){
        searchResultsPage = accPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        int actImageCount = productInfoPage.getProductImagesCount();
        Assert.assertEquals(actImageCount,imageCount);
    }

    @Test
    public void productInfoTest(){
        searchResultsPage = accPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String,String> actualProductDetailsMap = productInfoPage.getProductDetailsMap();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualProductDetailsMap.get("Brand"),"Apple");
        softAssert.assertEquals(actualProductDetailsMap.get("Product Code"),"Product 18");
        softAssert.assertEquals(actualProductDetailsMap.get("Reward Points"),"800");
        softAssert.assertEquals(actualProductDetailsMap.get("Availability"),"Out Of Stock");
        softAssert.assertEquals(actualProductDetailsMap.get("productPrice"),"$2,000.00");
        softAssert.assertEquals(actualProductDetailsMap.get("extaxprice"),"$2,000.00");
        softAssert.assertAll();

    }

}
