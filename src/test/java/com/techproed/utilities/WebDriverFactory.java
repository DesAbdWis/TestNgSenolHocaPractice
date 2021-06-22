package com.techproed.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static WebDriver getBrowser(String name){
        WebDriver driver;
        if(name.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            return driver= new ChromeDriver();
        }else if(name.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return driver=new FirefoxDriver();
        }else return null;
    }
}
