package fr.example.mareu.service;

import java.util.Date;
import java.util.List;

import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;

public interface ApiServiceMeetings {

    List<Meeting> getMeetingList();

    void createMeeting(Meeting meeting);

    void deleteMeetingItem(Meeting meeting);

    boolean isMeetingCanBeCreated(Date date, Room room);

    List<Meeting> filterDateHour(Date beginDate, Date EndDate);

    List<Meeting> filterRooms(List<Room> rooms);
}


