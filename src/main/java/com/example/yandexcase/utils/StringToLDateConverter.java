package com.example.yandexcase.utils;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Converter
public class StringToLDateConverter implements AttributeConverter<String, LocalDateTime> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public LocalDateTime convertToDatabaseColumn(String s) {


        return LocalDateTime.parse(s,formatter);
    }

    @Override
    public String convertToEntityAttribute(LocalDateTime date) {
        return formatter.format(date);
    }


}