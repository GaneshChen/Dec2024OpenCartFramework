package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;

public class AccountsPageTest extends BaseTest {

    //BeforeTest will Execute First followed by BeforeClass

    @BeforeClass
    public void accPageSetup(){
       accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

    }
    @Test
    public void accPageTitleTest(){
        Assert.assertEquals(accPage.getAccPageTitle(),HOME_PAGE_TITLE);
    }
    @Test
    public void accPageUrlTest(){
          Assert.assertTrue(accPage.getAccPageUrl().contains(HOME_PAGE_FRACTION_URL));
    }
    @Test
    public void accPageHeadersTest(){
      List<String> actHeadersList =   accPage.getAccPageHeaders();
      Assert.assertEquals(actHeadersList,expectedAccPageHeaderssList);
    }

}
