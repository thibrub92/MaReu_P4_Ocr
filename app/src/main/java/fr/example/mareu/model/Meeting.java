package fr.example.mareu.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Meeting implements Serializable {

    private String subject;
    private List<Workmate> participants;
    private Room room;
    private Date date;

    public Meeting(String subject, List<Workmate> participants, Room room, Date date) {
        this.subject = subject;
        this.participants = participants;
        this.room = room;
        this.date = date; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Workmate> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Workmate> participants) {
        this.participants = participants;
    }

    public Room getRoom() { return room; }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate(){
        String mFormatHour = "HH'h'mm";
        SimpleDateFormat formatHour = new SimpleDateFormat(mFormatHour);
        return formatHour.format(this.date); }

    public void setDate(Date date) {
        this.date = date;
    }
}
