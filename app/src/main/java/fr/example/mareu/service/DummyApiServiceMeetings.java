package fr.example.mareu.service;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.example.mareu.R;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;

public class DummyApiServiceMeetings implements ApiServiceMeetings {

    private List<Meeting> meetings = DummyMeetingsGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeetingList() {  //OK
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) { //OK
        meetings.add(meeting);
    }

    @Override
    public void deleteMeetingItem(Meeting meeting) {//OK
        meetings.remove(meeting);
    }

    @Override
    public boolean isMeetingCanBeCreated(Date date, Room room) {
        for (Meeting m : meetings) {
            Date meetingDate = m.getDate();
            long timeDifference = meetingDate.getTime() - date.getTime();
            long differenceMinute = timeDifference / (1000 * 60);

            if (differenceMinute > -60 && differenceMinute <= 59) {    // si même créneau horaire
                if (m.getRoom() == room) {                            // Si même room
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Meeting> filterDateHour(Date beginDate, Date endDate) {
        List<Meeting> filteredMeetings = new ArrayList<>();

        for (Meeting m : meetings) {
            // check que la date "m" est entre les 2 date debut/fin
            if (m.getDate().after(beginDate) && m.getDate().before(endDate)) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }

    @Override
    public List<Meeting> filterRooms(List<Room> rooms) {
        List<Meeting> filteredMeetings = new ArrayList<>();

        for (Meeting m : meetings) {
            if (rooms.contains(m.getRoom())) {
                filteredMeetings.add(m);
            }
        }
        return filteredMeetings;
    }
}

