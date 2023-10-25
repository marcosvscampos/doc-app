package org.roguesoft.docapp.utils;

import java.sql.Time;
import java.util.Date;

public class DateParser {

    public static Date parseData(final String value){
        Date parsedDate = org.roguesoft.docapp.infrastructure.utils.DateParser.parse(value, "dd/MM/yyyy");
        return new Date(parsedDate.getTime());
    }

    public static Time parseHorario(final String value){
        Date parsedHour = org.roguesoft.docapp.infrastructure.utils.DateParser.parse(value, "HH:mm");
        return new Time(parsedHour.getTime());
    }

}
