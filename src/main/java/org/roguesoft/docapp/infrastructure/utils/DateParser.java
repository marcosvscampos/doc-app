package org.roguesoft.docapp.infrastructure.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateParser {

    private DateParser(){}

    public static Date parse(final String value, final String format){
        try {
            if (Objects.nonNull(value) && !value.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.parse(value);
            }
            throw new RuntimeException("Não é possivel converter uma data/hora vazia");
        } catch (ParseException pe){
            throw new RuntimeException("Erro ao converter data/hora: " + value);
        }
    }

    public static String formatDate(final Date value, final String format){

        if (Objects.nonNull(value)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(value);
        }
        throw new RuntimeException("Não é possivel converter uma data/hora vazia");

    }

    public static String formatTime(final Time time, final String format){
        LocalTime localTime = time.toLocalTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(format);
        return localTime.format(timeFormatter);
    }

}
