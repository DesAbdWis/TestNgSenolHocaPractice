package com.techproed.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDSample {

    @DataProvider //bu method object dondurur Array olarak
    public Object[][] testData(){ //object olunca hem int hem String alir sikinti cikartmaz..

        String[][] data={

                {"Persons of Interest", "9"},
                {"Sherlock", "9.5"},
                {"Breaking Bad","10"},
                {"Lucifer","9"},
                {"Criminal Minds","9.5"},
                {"Prison Break","10"}

        };

        return data;
    }


    @Test(dataProvider = "testData") //DataProvider annotation altindaki method girilir..bu test icin datayi testData methodundan al demektir.
    public void test1(String dizi, String imdb){

        System.out.println("Dizinin ismi : "+dizi+" imdb puani: "+ imdb);

    }

}