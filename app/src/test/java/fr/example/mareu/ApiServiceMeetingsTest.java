package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import fr.example.mareu.DI.DI;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.DummyApiServiceMeetings;

public class ApiServiceMeetingsTest {

    private ApiServiceMeetings apiServiceMeetings;

    @Before
    public void setUp() {
        apiServiceMeetings = DI.getApiServiceMeetings();  // new DummyApiServiceMeetings()
    }

    @Test
    public void getMeetingListTest() {
        List<Meeting>  meetings = apiServiceMeetings.getMeetingList();
        assertEquals(4, meetings.size());
        assertEquals(1, meetings.get(0).getParticipants().size());
        assertEquals(2, meetings.get(2).getParticipants().size());
        assertEquals(3, meetings.get(4).getParticipants().size());
    }

    @Test
    public void DatesList() {
        DummyApiServiceMeetings mDummyApiServiceMeeting = new DummyApiServiceMeetings();
        List<Meeting> meetings = mDummyApiServiceMeeting.getMeetingList();
//        String[] dates = mDummyApiServiceMeeting.getMeetingList(meetings);
//        assertEquals(dates.length, 3);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting deleteMeeting = apiServiceMeetings.getMeetingList().get(0);
        apiServiceMeetings.deleteMeetingItem(deleteMeeting);
        assertFalse(apiServiceMeetings.getMeetingList().contains(deleteMeeting));
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingAdd = new Meeting ("sujet test", "collaborateur" , "Luigi" ,"24/01/2022 09h30/10h30");
        apiServiceMeetings.createMeeting(meetingAdd);
        assertTrue(apiServiceMeetings.getMeetingList().contains(meetingAdd));
    }
}
