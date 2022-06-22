package com.example.yandexcase.utils;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Converter
public class StringToDateConverter implements AttributeConverter<String, Date> {

    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Override
    public Date convertToDatabaseColumn(String s) {


        try {
            return formater.parse(s.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convertToEntityAttribute(Date date) {
        return formater.format(date);
    }


}
