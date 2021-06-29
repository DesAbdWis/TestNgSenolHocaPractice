package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DemoQaPage {
    public DemoQaPage(){   //abstract class abstract method kullanmayi saglar
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy (css= "#addNewRecordButton")
    public WebElement addBttn;

    @FindBy (xpath = "//*[@class='rt-table']/div[1]/div/div")
    public List<WebElement> columNames;

    @FindBy (xpath = "//*[@class='rt-body']")
    public WebElement tbody;


    @FindBy (xpath = "//*[@class='rt-body']/div/div/div")
    public List<WebElement> allColums;

    @FindBy (xpath = "//*[@class='rt-body']/div/div/div")
    public List<WebElement> getFormElements;

    /*
    method olusturalim;
    return Type: WebElement
    parameter :String
     */

    public WebElement formElements(String formData){

        return Driver.get().findElement(By.id(formData));
    }








}
