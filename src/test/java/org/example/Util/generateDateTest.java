package org.example.Util;

import org.example.Util.generateDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class generateDateTest {

    generateDate generateDate = new generateDate();
    Calendar calendar = Calendar.getInstance();



    @Test
    public void testGenerateSuccess(){
        String Dateparam = "2020-09-09";

        Date date = new Date(120, 8, 9);


        Date result = org.example.Util.generateDate.generate(Dateparam);


        Assertions.assertEquals(date,result);
    }



}
