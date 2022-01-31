package fr.example.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import fr.example.mareu.DI.DI;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.model.Workmate;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.ApiServiceWorkMate;
import fr.example.mareu.service.DummyApiServiceMeetings;
import fr.example.mareu.service.DummyMeetingsGenerator;
import fr.example.mareu.service.DummyWorkMatesGenerator;

public class ApiServiceMeetingsTest {
    private ApiServiceMeetings apiServiceMeetings;


    @Before
    public void setUp() {
        apiServiceMeetings = DI.getApiServiceMeetings();
    }

    @Test
    public void getMeetingListTest() {
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
        Meeting meetingAdd = new Meeting ("sujet test", new ArrayList<>(), Room.LUIGI, new Date());
        apiServiceMeetings.createMeeting(meetingAdd);
        assertTrue(apiServiceMeetings.getMeetingList().contains(meetingAdd));
        assertEquals(listSize +1, apiServiceMeetings.getMeetingList().size());
    }

    @Test
    public void isMeetingCanBeCreatedTest() {
        // same date(less than one hour) and same room
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 17);
        calendar1.set(Calendar.MINUTE, 30);
        calendar2.set(Calendar.HOUR_OF_DAY, 17);
        calendar2.set(Calendar.MINUTE, 0);
        Meeting meeting1 = new Meeting("sujet test", new ArrayList<>(), Room.LUIGI, calendar1.getTime());
        apiServiceMeetings.createMeeting(meeting1);
        assertFalse(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.LUIGI));

        // Same date but different room
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.MARIO));

        // Same date(more than one hour) and same room
        calendar2.set(Calendar.HOUR_OF_DAY, 16);
        calendar2.set(Calendar.MINUTE, 30);
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.LUIGI));
        calendar2.set(Calendar.HOUR_OF_DAY, 18);
        calendar2.set(Calendar.MINUTE, 30);
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar2.getTime(), Room.LUIGI));

        // Differente date same room
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.HOUR_OF_DAY, 10);
        calendar3.set(Calendar.MINUTE, 0);
        assertTrue(apiServiceMeetings.isMeetingCanBeCreated(calendar3.getTime(), Room.LUIGI));
    }
}
