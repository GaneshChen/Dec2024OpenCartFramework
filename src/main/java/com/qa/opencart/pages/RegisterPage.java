package com.qa.opencart.pages;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By passwordConfirm = By.id("input-confirm");

    private By subscribeYes = By.xpath("(//label [@class ='radio-inline'])[1]/input[@type='radio']");
    private By subscribeNo = By.xpath("(//label [@class ='radio-inline'])[2]/input[@type='radio']");

    private By agreeCheckBox = By.name("agree");
    private By continueBtn = By.xpath("//input[@value='Continue']");

    private By successMsg = By.cssSelector("div#content h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public boolean userRegistration(String firstName, String lastName,
                                     String telephone, String password, String subscribe){

        elementUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_TIMEOUT).sendKeys(firstName);
        elementUtil.doSendKeys(this.lastName,lastName);
        elementUtil.doSendKeys(this.email, StringUtils.getRandomEmailId());
        elementUtil.doSendKeys(this.telephone,telephone);
        elementUtil.doSendKeys(this.password,password);
        elementUtil.doSendKeys(this.passwordConfirm,password);

        if(subscribe.equalsIgnoreCase("Yes")){
            elementUtil.doClick(subscribeYes);
        }
        else {
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doClick(agreeCheckBox);
        elementUtil.doClick(continueBtn);

        elementUtil.waitForElementVisible(successMsg,AppConstants.MEDIUM_DEFAULT_TIMEOUT);

        if(elementUtil.doGetElementText(successMsg).contains(AppConstants.REGISTER_SUCCESS_MESSGAGE)){
            elementUtil.doClick(logoutLink);
            elementUtil.doClick(registerLink);
            return true;
        }
        return false;
    }
}
