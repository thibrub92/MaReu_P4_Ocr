package fr.example.mareu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Meeting;

public class DummyApiServiceMeetings implements ApiServiceMeetings {

    private List<Meeting> reunions = DummyCollaborateursGenerator.generateReunion();

    private Map <String, Date> mDate;

    @Override
    public List<Meeting> getListReunions() {
        return reunions;
    }

    @Override
    public void createReunion(Meeting meeting) {
        reunions.add(meeting);
    }

    @Override
    public void deleteReunionItem(Meeting meeting) {
        reunions.remove(meeting);
    }
}
