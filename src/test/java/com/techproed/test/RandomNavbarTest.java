package com.techproed.test;

import com.techproed.pages.Dashboard;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class RandomNavbarTest {
    Dashboard dashboard = new Dashboard();

    @BeforeMethod
    public void test() {
        Driver.get().get("hepsiburada_url");
    }

    @Test
    public void test1(int a) throws InterruptedException {
        /* dashboard.navBar("Moda");
        List<WebElement> tumLinkler = Driver.get().findElements(By.xpath("//*[@class='sf-ChildMenuItems-3VA7f']//li/a//*[@class='sf-ChildMenuItems-3VA7f']//li/a"));
        List<String> subMenuText = dashboard.getElementsText(tumLinkler);

        // int x = subMenuText.size(); //moda=80,  anne=190

        // Random random =new Random();
        // int index = random.nextInt(subMenuText.size()); lazy way bu 2 satir yerine asagidaki satir yazilabilir..

        String subTitle = subMenuText.get(new Random().nextInt(subMenuText.size())); //buradaki get() :int index alir
        dashboard.navBar("moda",subTitle);

       // Assert.assertEquals(Driver.get().getTitle(), subTitle); de olur

        if (Driver.get().getTitle().contains(subTitle)) {
            System.out.println("Pass");
        }else {
            System.out.println("Fail");
            System.out.println("Expected title " + subTitle);
            System.out.println("Actual title :" + Driver.get().getTitle() );
        }

    }

     */
        Assert.assertTrue(dashboard.checkNavbar("moda"));

    }
}


