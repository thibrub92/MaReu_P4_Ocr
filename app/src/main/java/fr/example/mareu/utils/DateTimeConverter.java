package fr.example.mareu.utils;

import java.util.Locale;

public class DateTimeConverter {

    public static String getFormattedTime(int hour, int minute) {
        return String.format(Locale.getDefault(),"%02dh%02d", hour, minute);
    }

    public static String getFormattedDate(int year, int month, int day) {
        month = month + 1;
        return String.format(Locale.getDefault(), "%02d/%02d/%d", day, month, year);
    }
}
