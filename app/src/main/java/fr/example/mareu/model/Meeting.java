package fr.example.mareu.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Meeting implements Serializable {

    private int id;

    private  String subject;

    private  List<String> participants;

    private  Room room;

    private LocalTime time;

    public Meeting(String subject, List<String> participants,Room room, LocalTime time){
        this.subject= subject;
        this.participants= participants;
        this.room= room;
        this.time= time;
        this.id= id;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }
    public String getMeeting(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject= subject;
    }
    public List<String>getParticipants(){
        return participants;
    }
    public void setParticipants(java.util.List<String> participants) {
        this.participants = participants;
    }
    public LocalTime getTime(){
        return time;
    }
    public void setTime(LocalTime time){
        this.time= time;
    }
    public Room getRoom(){
        return room;
    }
    public void setRoom(Room room){
        this.room= room;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, time, participants, room);
    }
}
