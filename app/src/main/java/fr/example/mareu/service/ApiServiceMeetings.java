package fr.example.mareu.service;

import java.util.List;

import fr.example.mareu.model.Meeting;

public interface ApiServiceMeetings {
    List<Meeting> getMeetingList();

    void createMeeting(Meeting meeting);

    void deleteMeetingItem(Meeting meeting);
}
