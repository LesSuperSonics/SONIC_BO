package com.capgemini.candidateorganizationsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesConversion {
    public static Date getDateFromString(String dateInString) throws  ParseException{
           SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
           return  formatter.parse(dateInString);
    }





}
