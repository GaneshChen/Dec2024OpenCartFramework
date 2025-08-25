package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
   private WebDriver driver;
   private ElementUtil eleUtil;

   private final By productHeader = By.tagName("h1");
   private final By productImages = By.cssSelector("ul.thumbnails img");

   private final By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");

   private final By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

   private Map<String,String> productMap;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        this.eleUtil = new ElementUtil(driver);
    }

    public String getProductHeader(){
       String header = eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();
       System.out.println("Product Header :"+ header);
       return header;
    }
    public int getProductImagesCount(){
       int imageCount =
               eleUtil.waitForElementsVisible(productImages,AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
        System.out.println("Total Number of Product Images :"+imageCount);
        return imageCount;
    }


    //Brand: Apple
    //Product Code: Product 18
    //Reward Points: 800
    //Availability: Out Of Stock

    public void getProductMetaData(){
        productMap = new HashMap<>();
        List<WebElement> MetaList = eleUtil.waitForElementsVisible(productMetaData,AppConstants.DEFAULT_TIMEOUT);
        for(WebElement e : MetaList){
            String metaData = e.getText();
            String[] meta = metaData.split(":");
            String metaKey = meta[0].trim();
            String metaValue = meta[1].trim();
            productMap.put(metaKey,metaValue);
        }
    }
    //$2,000.00
    //Ex Tax: $2,000.00
    public void getProductPriceData(){
        List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData,AppConstants.DEFAULT_TIMEOUT);
        String productPrice = priceList.get(0).getText();
        String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
        productMap.put("productPrice",productPrice);
        productMap.put("extaxprice",exTaxPrice);
    }

    public Map<String, String> getProductDetailsMap(){
        productMap= new HashMap<>();
        //Linked HashMap maintains the Insertion Order
        //TreeMap Maintains in Sorted Order by Keys
        productMap.put("productHeader",getProductHeader());
        productMap.put("productImages",String.valueOf(getProductImagesCount()));
        getProductMetaData();
        getProductPriceData();
        System.out.println("Full Product Details :"+productMap);
        return productMap;
    }

}
