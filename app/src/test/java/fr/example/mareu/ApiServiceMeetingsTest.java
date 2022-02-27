package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.example.mareu.DI.DI;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.DummyMeetingsGenerator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiServiceMeetingsTest {
    private ApiServiceMeetings apiServiceMeetings;

    @Before
    public void setUp() {
        apiServiceMeetings = DI.getApiServiceMeetings();
    }

    @Test
    public void agetMeetingListTest() {
        List<Meeting> meetingList = apiServiceMeetings.getMeetingList();
        List<Meeting> expectedListMeetings = DummyMeetingsGenerator.MEETING_LAMZONE;
        assertThat(meetingList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedListMeetings.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting deleteMeeting = apiServiceMeetings.getMeetingList().get(0);
        apiServiceMeetings.deleteMeetingItem(deleteMeeting);
        assertFalse(apiServiceMeetings.getMeetingList().contains(deleteMeeting));
    }

    @Test
    public void createMeetingWithSuccess() {
        int listSize = apiServiceMeetings.getMeetingList().size();
        Meeting meetingAdd = new Meeting("sujet test", new ArrayList<>(), Room.LUIGI, new Date());
        apiServiceMeetings.createMeeting(meetingAdd);
        assertTrue(apiServiceMeetings.getMeetingList().contains(meetingAdd));
        assertEquals(listSize + 1, apiServiceMeetings.getMeetingList().size());
    }

    @Test
    public void isMeetingCanBeCreatedTest() {
        // same date(less than one hour) and same room
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 1);
        calendar1.set(Calendar.MINUTE, 30);
        calendar2.set(Calendar.HOUR_OF_DAY, 1);
        calendar2.set(Calendar.MINUTE, 0);
        Meeting meeting1 = new Meeting("sujet test", new ArrayList<>(), Room.LUIGI, calendar1.getTime());
        apiServiceMeetings.createMeeting(meeting1);
        assertFalse(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.LUIGI));

        // Same date but different room
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.MARIO));

        // Same date(more than one hour) and same room
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 30);
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.LUIGI));
        calendar2.set(Calendar.HOUR_OF_DAY, 2);
        calendar2.set(Calendar.MINUTE, 30);
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.LUIGI));

        // Differente date same room
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.HOUR_OF_DAY, 3);
        calendar3.set(Calendar.MINUTE, 0);
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar3.getTime(), Room.LUIGI));
    }
}
