package org.example.Util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class generateDate {
    public static Date generate(String Dateparam){
        Date date = null;
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(Dateparam);

        }
        catch(ParseException e){
            System.err.println(e.getMessage());

        }
        return date;


    }
}
