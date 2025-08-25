package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {

    @BeforeClass
    public void registerSetUp(){
       registerPage = loginPage.navigateToRegisterPage();
   }

   @DataProvider
   public Object[][] getUserRegTestData(){
        return new Object[][]{
                {"Ganeeh","Meeta","1112223332","password2","YES"},
                {"Kurresh","Meeta","1112223332","password2","yes"},
                {"Maneesh","Meeea","1112223333","password3","No"}
        };
   }

   @DataProvider
   public Object[][] getUserRegData(){
      Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
      return regData;
   }

   @DataProvider
   public Object[][] getProductCSVData(){
        return CSVUtil.csvData("product");
   }

   @Test(dataProvider = "getUserRegTestData")
   public void userRegisterTest(String firstName, String lastName, String telephone,
    String password, String subscribe){
       Assert.assertTrue(registerPage.userRegistration(firstName,lastName,telephone,password,subscribe));
       }

}
