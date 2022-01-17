package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.List;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.service.DummyApiServiceMeetings;

public class MeetingTest {

    @Test
    public void createMeetingsList() {
        List<Meeting> reunions = new DummyApiServiceMeetings().getMeetingList();
        assertEquals(24, reunions.size());
        assertEquals(1, reunions.get(0).getParticipants().size());
        assertEquals(2, reunions.get(2).getParticipants().size());
        assertEquals(3, reunions.get(4).getParticipants().size());

    }

    @Test
    public void DatesList() {
        DummyApiServiceMeetings mDummyApiServiceMeeting = new DummyApiServiceMeetings();
        List<Meeting> meetings = mDummyApiServiceMeeting.getMeetingList();
        String[] dates = mDummyApiServiceMeeting.getMeetingList(meetings);
        assertEquals(dates.length, 3);
    }
}
