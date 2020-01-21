package com.ashu.obviousnote.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonMethods {

    public static Date getCurrentDateTime() {
        Date currentDate = Calendar.getInstance().getTime();
        return currentDate;
    }

    public static String getFormattedDateString(Date date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            String dateString = simpleDateFormat.format(date);
            Date newDate = simpleDateFormat.parse(dateString);
            simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");

            SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
            String displayValue = timeFormatter.format(date);

            return simpleDateFormat.format(newDate) + ", " + displayValue;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
