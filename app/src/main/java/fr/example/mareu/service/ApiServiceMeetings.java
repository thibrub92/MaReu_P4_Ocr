package fr.example.mareu.service;

import java.util.Date;
import java.util.List;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;

public interface ApiServiceMeetings {

    List<Meeting> getMeetingList();

    void createMeeting(Meeting meeting);

    void deleteMeetingItem(Meeting meeting);

    boolean isMeetingCanBeCreated(Date date , Room room);

    List<Meeting> filterDateHour (List<Meeting> meetings, List<Room> rooms, Date date);

    List<Room> filterRooms (List<Meeting> meetings, List<Room> rooms, Date date);

    List<Meeting> withoutFilter (List<Meeting> meetings);
}


