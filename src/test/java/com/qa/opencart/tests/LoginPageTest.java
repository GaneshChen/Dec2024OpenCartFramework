package com.qa.opencart.tests;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.qa.opencart.constants.AppConstants.*;

@Feature("F 50: Open Cart - Login Feature")
@Epic("Epic 100: Design Pages for open cart Application")
@Story("US 101: Implement login Page for open cart Application")

public class LoginPageTest extends BaseTest {

    @Severity(SeverityLevel.MINOR)
    @Owner("Ganesh")
    @Description("Checking OpenCart Login Title")
    @Test
    public void loginPageTitleTest(){
        String actTitle = loginPage.getLoginPageTitle();
        ChainTestListener.log("Checking login page title " + actTitle);
        Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
    }
    @Test
    public void loginPageUrlTest(){
        String actUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL));
    }
    @Description("Checking Open Cart Login Page has forgot pwd link")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Ganesh")
    @Test
    public void forgotPwdLinkExistTest(){
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }
    @Description("Check user is able to login with valid user credentials..")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Ganesh")
    @Test (priority = Short.MAX_VALUE)
    public void doLoginTest(){
       accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
       Assert.assertEquals(accPage.getAccPageTitle(),HOME_PAGE_TITLE);
    }

    //In the below case, the Test won't be Executed, since enabled is false
    @Test(enabled = false)
    public void forgotPwdLink(){
        System.out.println("Testing is In-Progress");
    }
}
