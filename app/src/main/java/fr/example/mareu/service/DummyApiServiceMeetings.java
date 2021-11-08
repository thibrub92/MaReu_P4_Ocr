package fr.example.mareu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import fr.example.mareu.model.Meeting;

public class DummyApiServiceMeetings implements ApiServiceMeetings {

    private List<Meeting> meetings = DummyMeetingsGenerator.generateMeeting();


    @Override
    public List<Meeting> getMeetingList() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeetingItem(Meeting meeting) {
        meetings.remove(meeting);
    }
}
