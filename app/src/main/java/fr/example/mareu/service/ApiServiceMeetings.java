package fr.example.mareu.service;

import java.util.List;

import fr.example.mareu.model.Meeting;

public interface ApiServiceMeetings {
    List<Meeting> getListReunions();

    void createReunion(Meeting meeting);

    void deleteReunionItem(Meeting meeting);
}
