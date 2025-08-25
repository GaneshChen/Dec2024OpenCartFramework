package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SearchResultsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private final By resultsProduct = By.cssSelector("div.caption  a");
    public SearchResultsPage(WebDriver driver) {
        this.driver= driver;
        eleUtil = new ElementUtil(driver);
    }

    public int getResultsProductCount(){
    int searchCount =  eleUtil.waitForElementsVisible(resultsProduct, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
    System.out.println("Total Number of Search Products:" + searchCount);
    return searchCount;
    }

    public ProductInfoPage selectProduct(String productName){
        System.out.println("The Product Name is :"+productName);
        eleUtil.doClick(By.linkText(productName));
        return new ProductInfoPage(driver);
    }


}
