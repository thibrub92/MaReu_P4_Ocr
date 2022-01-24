package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import fr.example.mareu.DI.DI;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.ApiServiceWorkMate;
import fr.example.mareu.service.DummyApiServiceMeetings;
import fr.example.mareu.service.DummyWorkMatesGenerator;

public class ApiServiceMeetingsTest {

    private ApiServiceMeetings apiServiceMeetings;
    private ApiServiceWorkMate apiServiceWorkMate;

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
        Meeting meetingAdd = new Meeting ("sujet test", "collaborateur" , "Luigi" ,"24/01/2022","9h00");
        apiServiceMeetings.createMeeting(meetingAdd);
        assertTrue(apiServiceMeetings.getMeetingList().contains(meetingAdd));
    }

    @Test
    public void getListWormatesWithSuccess(){
        List<Workmate> w = apiServiceWorkMate.getWorkmateList;
        List<Workmate> expectedListEmployees = DummyWorkMatesGenerator.WORKMATE_LAMZONE;
        assertThat(w, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListEmployees.toArray()));

    }

    @Test
    public void addNewMeetingWithSuccess() {

        List<Workmate> workmateList = Arrays.asList(new Workmate ("test", "test", "test","test"),
                new Workmate ("Fanny", "test", "test","test"),
                new Workmate ("Fanny", "test", "test","test"));


        Meeting newMeeting = new Meeting("test",
                "participant",
                "10/02/22",
                "15:30",
                "16:00",
                "Revues des dernières actions",
                "test"
                workmateList);

        apiServiceMeetings.createMeeting(newMeeting);
        assertTrue(apiServiceMeetings.getMeetingList().contains(newMeeting));
    }

    @Test
    public void removeMeetingWithSuccess() {

        Meeting meetingRemove = apiServiceMeetings.getMeetingList().get(1);
        apiServiceMeetings.getMeetingList().remove(meetingRemove);
        assertFalse(apiServiceMeetings.getMeetingList().contains(meetingRemove));
    }
}
