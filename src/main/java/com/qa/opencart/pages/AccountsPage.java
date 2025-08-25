package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;
public class AccountsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    //private By Locators
     private final By headers = By.cssSelector("div#content > h2");
     private final By search = By.xpath("//input[@name='search']");
     private final By searchIcon = By.cssSelector("div#search button");

    //public Page Constructor
    public AccountsPage(WebDriver driver){
        this.driver = driver;
        this.eleUtil = new ElementUtil(driver);
    }

    public String getAccPageTitle() {
        String title = eleUtil.waitForTitleIs(HOME_PAGE_TITLE,DEFAULT_TIMEOUT);
        System.out.println("Home Page Title is :"+ title);
        return title;
    }

    public String getAccPageUrl() {
        String url =eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL,DEFAULT_TIMEOUT);
        System.out.println("Home Page URL is:"+url);
        return url;
    }

    public List<String> getAccPageHeaders(){
       List<WebElement> headerList =  eleUtil.getElements(headers);
       List<String> headerValueList = new ArrayList<>();
       for(WebElement e : headerList){
           headerValueList.add(e.getText());
       }
        return headerValueList;
    }

    public SearchResultsPage doSearch(String searchKey){
        System.out.println("Search Key :"+ searchKey);
        eleUtil.doSendKeys(search,searchKey);
        eleUtil.doClick(searchIcon);
        return new SearchResultsPage(driver);
    }

}
