package com.qa.opencart.utils;


import com.aventstack.chaintest.plugins.ChainTestListener;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.PanelUI;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ElementUtil {

    private WebDriver driver;
    private Actions act;

    public ElementUtil(WebDriver driver) {

        this.driver = driver;
        act = new Actions(driver);
    }

    public void doSendKeys(By locator, String value) {
              getElement(locator).clear();
              getElement(locator).sendKeys(value);
    }



    public void doSendKeys(WebElement element,String value){
        element.clear();
        element.sendKeys(value);
    }
    public void doSendKeys(By locator, CharSequence... value) {
        getElement(locator).sendKeys(value);
    }

    public void doClick(By locator) {
        getElement(locator).click();
    }

    public String doGetElementText(By locator) {
        String eleText = getElement(locator).getText();
        if (eleText != null)
            return eleText;
        else {
            System.out.println("Element Text is null: " + eleText);
            return null;
        }
    }

    public WebElement waitForElementVisible(By locator,int timeout){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForElementsVisible(By locator,int timeout){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            return Collections.EMPTY_LIST;
        }
    }


    public boolean isElementDispalyed(By locator) {

        try {
            return getElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element is NOT Displayed :" + locator);
            return false;
        }
    }

    public String doElementGetAttribute(By locator, String atrrName) {
        return getElement(locator).getAttribute(atrrName);
    }


    public boolean doSearch(By searchField, By suggestions, String searchKey, String matchValues) throws InterruptedException {

        boolean flag = false;
        doSendKeys(searchField, searchKey);

        List<WebElement> suggList = getElements(suggestions);
        int totalSuggestions = suggList.size();
        System.out.println("Total Number of Suggestions " + totalSuggestions);

        if (totalSuggestions == 0) {
            System.out.println("No Suggestions found....");

        }

        for (WebElement e : suggList) {
            String text = e.getText();
            System.out.println(text);
            if (text.contains(matchValues)) {
                e.click();
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println(matchValues + "is found");
            return true;
        } else {
            System.out.println(matchValues + "is NOT Found");
            return false;
        }
    }

    @Step("Finding the element using : {0}")
    public WebElement getElement(By locator) {
        ChainTestListener.log("locator :" + locator.toString());
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public int getElementsCount(By locator) {
        return getElements(locator).size();
    }

    public boolean isElementNotPresent(By locator) {
        if (getElementsCount(locator) == 0) {
            return true;
        }
        return false;
    }

    public boolean isElementPresentMultipleTimes(By locator) {
        if (getElementsCount(locator) >= 1) {
            return true;
        }
        return false;
    }

    public int getDropDownOptionsCount(By locator) {
        Select select = new Select(getElement(locator));
        return select.getOptions().size();

    }

    public void selectDropDownValueByVisibleText(By locator, String visibleText) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(visibleText);
    }

    public void selectDropDownValueByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void selectDropDownValueByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public void selectDropDowbValue(By locator, String Value) {
        List<WebElement> optionsList = getElements(locator);
        System.out.println(optionsList.size());
        for (WebElement e : optionsList) {
            String text = e.getText();
            if (text.equals(Value)) {
                e.click();
                break;
            }
        }
    }

    public void ParentChildMenu(By level1,By level2, By level3, By level4) throws InterruptedException {

        doClick(level1);
        Thread.sleep(1000);
        act.moveToElement(getElement(level2)).perform();
        Thread.sleep(1000);
        act.moveToElement(getElement(level3)).perform();
        Thread.sleep(1000);
        doClick(level4);

    }

    public String waitForTitleContainsAndReturn(String fractionTitle, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            wait.until(ExpectedConditions.titleContains(fractionTitle));
            return driver.getTitle();
        } catch (Exception e) {
            System.out.println("Title is NOT Matches");
            return "-1";
        }
    }

    public boolean waitForTitleContains(String fractionTitle, int timeOut){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            return wait.until(ExpectedConditions.titleContains(fractionTitle));
        } catch (Exception e) {
            System.out.println("Title is NOT Matches");
            return false;
        }
    }

    public String waitForTitleIs(String title, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.titleIs(title));
            return driver.getTitle();
        } catch (TimeoutException e) {
            return null;
        }
    }
    public String waitForURLContains(String url, int timeout){
        try {
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.urlContains(url));
            return driver.getCurrentUrl();
        } catch (Exception e) {
            return null;
        }

    }

}
