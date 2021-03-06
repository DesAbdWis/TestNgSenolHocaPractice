package com.techproed.pages;

import com.techproed.utilities.BrowserUtils;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public abstract class BasePage {

    public BasePage(){   //abstract class abstract method kullanmayi saglar
        PageFactory.initElements(Driver.get(),this);
    }

    public abstract boolean checkTitle(); //abstract method, Dashboard classta olusturduk..
    String menuElement = "menu";

    String path = "//span[contains(*,'" + menuElement + "')]";

    //Driver.get().findElement(By.xpath(path));
    By ustMenu = By.xpath("//span[contains(*,'" + menuElement + "')]");


  /*
    method olusturalim..return type :WebElement
     parameter String : (moda,Elektronik vs)
     */

    public WebElement getNavMenu(String menuName){ //Moda, Elektronkik,

        By ustMenu=By.xpath("//span[contains(*,'"+menuName+"')]");

        WebElement element=Driver.get().findElement(ustMenu);

        return element;
    }

    /*
    Method olusturup alt menulere tiklayalim
    Return type :void
    parameter :String, String
    */
    public void navBar(String menuName, String subMenu) throws InterruptedException { //Moda, Pantolon
/*
        // By ustMenu = By.xpath("//span[contains(*,'" + menuName + "')]");
        // By altMenu = By.xpath("//span[contains(*,'" + subMenu + "')]");
        // bu 2 satir yerine direk asagida icerde yazabiliriz..lazy way

        WebElement element=Driver.get().findElement(By.xpath("//span[contains(*,'"+ menuName +"')]"));
        element.click();
        Thread.sleep(5000);

        WebElement altElm=Driver.get().findElement(By.xpath("//a/span[text()='"+ subMenu +"']"));
        altElm.click();
        Thread.sleep(5000);
     */
        //yukaridakileri  try catch ile yapalim
        WebElement element = Driver.get().findElement(By.xpath("//span[contains(*,'" + menuName + "')]"));
        WebElement altElm=Driver.get().findElement(By.xpath("//a/span[text()='"+ subMenu +"']"));
        Actions actions= new Actions(Driver.get());

        try { //hata almamiza ragmen execution nin devam etmesi icin try-catch block kullaniyoruz..exception i catch ediyor
            element.click();
        } catch (NoSuchElementException e){  // child exception
            //e.printStackTrace(); yerine
            actions.click(element).perform();
        } catch (Exception e) {   //parent exception
            BrowserUtils.clickWithJS(element);
        }
        //child :NoSuchElementException  iken  parent:Exception
        //child :Exception  iken parent:Throwable
        try {
            altElm.click();
        } catch (Exception e){
            actions.click(altElm).perform();
        }  catch (Throwable t) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.get();
            js.executeScript("arguments[0].click();", element, altElm); //virgulden sonra yazilanlar arguments[] arrayine atilir
             //arguments[0]= element.click()   arguments[1]= altElm.click() olacaktir
        }

        //Java Seleniumda sendKeys() yerine JavaScript Executor ile yapabiliriz..js.executeScript(arguments[0].value("asdf"))
        //Actions class kullanmadan Javascript Executor ile hoverover yapilabilir js.executeScript("arguments[0].scrollIntoView(true);", element);
    //sonsuz bir loop dan try-catch ile exception alarak cikabiliriz
    }

    public void navBar(String menuName) throws InterruptedException {  // isim ayni farkli parametre sayisi : overloading ...Moda
        Thread.sleep(5000);
        WebElement element=Driver.get().findElement(By.xpath("//span[contains(*,'"+ menuName +"')]"));
        element.click();
    }

    /*
    method olusturalim
    return = List<String>
    parameter = List<Webelement>
     */
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /*
     method olusturalim
    return type :boolean
    parameter String : Moda, Ev, Yasam
     */
    public boolean checkNavbar(String menu) throws InterruptedException {
        navBar("menu");
        List<WebElement> tumLinkler = Driver.get().findElements(By.xpath("//*[@class='sf-ChildMenuItems-3VA7f']//li/a//*[@class='sf-ChildMenuItems-3VA7f']//li/a"));
        List<String> subMenuText = getElementsText(tumLinkler);

        System.out.println(subMenuText);

        int x = subMenuText.size(); //moda=80,  anne=190

        Random random =new Random();
        int index = random.nextInt(subMenuText.size()); //lazy way bu 2 satir yerine asagidaki satir yazilabilir..

        String subTitle = subMenuText.get(new Random().nextInt(subMenuText.size())); //buradaki get() :int index alir
        navBar("menu",subTitle);
        Thread.sleep(3000);

        // Assert.assertEquals(Driver.get().getTitle(), subTitle); de olur ancak Assertion method icinde yazilmaz..

        if (Driver.get().getTitle().contains(subTitle)) {
            return true;
        }else {
            System.out.println("Expected title " + subTitle);
            System.out.println("Actual title :" + Driver.get().getTitle() );
            return false;
        }


    }


}
