package fr.example.mareu.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateHour implements Serializable {

public Calendar mCalendar;
public int mDate;
public int mHour;

    String mFormatDate =  "HH'h '/ mm";
    String mFormatHour = "dd/MM/yyyy";

    SimpleDateFormat formatDate = new SimpleDateFormat(mFormatDate);
    SimpleDateFormat formatHour = new SimpleDateFormat(mFormatHour);

    public void DateHour (int date, int hour) {
        this.mDate = date;
        this.mHour = hour; }

    public int getmDate(){
        return mDate;
    }
    public void setmDate( int date){mDate= date; }

    public int getmHour(){
        return mHour;
    }
    public void setmHour(int hour){mHour= hour;}

    public Calendar getCalendar() {
        return mCalendar;
    }
}
