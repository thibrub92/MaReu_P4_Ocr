package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.example.mareu.DI.DI;
import fr.example.mareu.model.Meeting;
import fr.example.mareu.model.Room;
import fr.example.mareu.service.ApiServiceMeetings;
import fr.example.mareu.service.ApiServiceRoom;
import fr.example.mareu.service.ApiServiceWorkMate;
import fr.example.mareu.service.DummyApiServiceMeetings;

public class FiltersTest {

    private DummyApiServiceMeetings mDummyApiServiceMeetings;

    public class DummyApiServiceReunionsTest {
        private ApiServiceRoom serviceRoom;
        private ApiServiceMeetings serviceMeetings;
        private ApiServiceWorkMate serviceWorkMate;

        private int ITEMS_COUNT = 24;
        private int ITEMS_COUNT_SALLE = 4;
        private int ITEMS_COUNT_DATE = 12;
        private int ITEMS_COUNT_SALLE_DATE = 2;

        private String filterDate = "31/01/2022";

        @Before
        public void setup() {

//            ApiServiceRoom = DI.getNewInstanceServiceMeeting();
//            ApiServiceMeetings = DI.getApiServiceMeetings().createMeeting(Meeting);
//
        }


    @Test
    public void filterRooms() {
        List<Room> salles = ApiServiceRoom.getRoomList().subList(0, 2);
        List<Meeting> meetings = mDummyApiServiceMeetings.getMeetingList();
        List<Meeting> meeting = mDummyApiServiceMeetings.filterRooms(meetings, );
        assertEquals(meeting.size(), meeting);
    }
}
}
