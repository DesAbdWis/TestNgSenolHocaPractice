package com.techproed.test;

import com.techproed.pages.Dashboard;
import com.techproed.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class HepsiBurada {

    Dashboard d1=new Dashboard();


    @BeforeMethod
    public void before(){
        Driver.get().get("https://www.hepsiburada.com/");
    }

    @Test
    public void test1() throws InterruptedException {

        WebElement moda = d1.getNavMenu("Moda");

        Actions actions=new Actions(Driver.get());

        actions.moveToElement(moda).perform();

        d1.getNavMenu("Ev, Ya").click();

        Thread.sleep(3000);



    }

    @Test
    public void test2() throws InterruptedException {

        d1.navBar("Moda","Pantolon");

    }

    @AfterMethod
    public void kapat(){
        Driver.get().quit();
    }
}
