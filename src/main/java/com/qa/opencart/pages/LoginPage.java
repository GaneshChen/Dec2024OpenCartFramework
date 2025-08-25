package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    //private By Locators
    private By email = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgotPwdLink = By.linkText("Forgotten Password");
    private By registerLink = By.linkText("Register");

    //public Page Constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.eleUtil = new ElementUtil(driver);
    }

      @Step("Getting Login Page Title")
      //Public Page Actions/ Methods
      public String getLoginPageTitle(){
        String title = eleUtil.waitForTitleIs(LOGIN_PAGE_TITLE,DEFAULT_TIMEOUT);
        System.out.println("Login Page Title is:"+title);
        return title;
      }

      @Step("Getting Login Page Url")
      public String getLoginPageUrl(){
        String url =eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL,DEFAULT_TIMEOUT);
        System.out.println("Login Page URL is:"+url);
        return url;
      }

      @Step("Checking forgot pwd link exists")
      public boolean isForgotPwdLinkExist(){
           return eleUtil.isElementDispalyed(forgotPwdLink);
     }

     @Step("Login with Valid UserName: {0} and password : {1}")
      public AccountsPage doLogin(String userName, String pwd){
          System.out.println("User Credentials "+userName + ": "+pwd);
          eleUtil.waitForElementVisible(email,MEDIUM_DEFAULT_TIMEOUT).sendKeys(userName);
          eleUtil.doSendKeys(password,pwd);
          eleUtil.doClick(loginBtn);
          String title = eleUtil.waitForTitleIs(HOME_PAGE_TITLE,DEFAULT_TIMEOUT);
          System.out.println("Accounts Page Title :"+ title);
          return new AccountsPage(driver);
    }

    @Step("Navigating to User Registration Page")
    public RegisterPage navigateToRegisterPage(){
        eleUtil.doClick(registerLink);
        return new RegisterPage(driver);
    }

}
