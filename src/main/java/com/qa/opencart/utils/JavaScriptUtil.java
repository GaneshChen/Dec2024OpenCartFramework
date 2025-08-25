package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public String getPageTitle(){
        return js.executeScript("return document.title;").toString();
    }

    public String getURL(){
        return js.executeScript("return document.URL;").toString();
    }

    public void generateJSAlert(String mseg){
        js.executeScript("alert('"+mseg+"')");

    }

    public String getPageInnerText(){
        return js.executeScript("return document.documentElement.innerText;").toString();
    }

    public void goBackWithJS(){
        js.executeScript("history.go(-1)");
    }

    public void goForwardWithJS(){
        js.executeScript("history.go(1)");
    }

    public void pageRefreshWithJS(){
        js.executeScript("history.go(0)");
    }
    //The below is been used to Zoom-In & Zoom-Out.
    //document.body.style.zoom = '100%'

    public void zoomInAndZoomOut(String zoomPercentage){
        String zoom = "document.body.style.zoom='"+zoomPercentage+"'";
        js.executeScript(zoom);
    }

    //This scroll to the bottom of the page.
    //window.scrollTo(0,document.body.scrollHeight);

    public void scrollPageDown(){
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

    public void scrollPageUp(){
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public void scrollPageDown(String height){
        js.executeScript("window.scrollTo(0,'"+height+"')");
    }

    public void drawBorder(WebElement element){
        js.executeScript("arguments[0].style.border='3px solid red'",element);
    }

    public void scrollToElement(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

}
