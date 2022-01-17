package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.example.mareu.model.Room;
import fr.example.mareu.service.DummyApiServiceMeetings;

public class FiltersTest {

    private DummyApiServiceMeetings mDummyApiServiceMeetings;

    @Before
    public void setupService() {
        mDummyApiServiceMeetings = mDummyApiServiceMeetings.newInstance();
    }


    @Test
    public void filterListByRoomWithSuccess(){

        mDummyApiServiceMeetings.getMeetingList();

        mDummyApiServiceMeetings.filterRooms(Room);

        mDummyApiServiceMeetings.filterRooms();

        assertEquals(4, mDummyApiServiceMeetings.getMeetingList().size());
        for(int i = 0; i < mDummyApiServiceMeetings.filterRooms(Room).size(); i++){
            assertEquals("Test", mDummyApiServiceMeetings.getListToDisplay().get(i).getMeetingRoom());
        }
    }

    @Test
    public void filterListByDateOption1WithSuccess(){

        DummyApiServiceMeetings.initListMeetingTest();

        String filter = "18/01/2022";

        mDummyApiServiceMeetings.filterRooms();



    }

    @Test
    public void filterListByDateOption2WithSuccess(){

        mDummyApiServiceMeetings.filterDateHour();

        String filterStart = "01/01/2022";
        String filterEnd = "31/01/2022";


        mDummyApiServiceMeetings.filterDateHour(filterListByDateOption1WithSuccess(), filterListByDateOption2WithSuccess());

        assertEquals(5, mDummyApiServiceMeetings.getMeetingList().size());
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = dateFormat.parse(filterStart);
            Date endDate = dateFormat.parse(filterEnd);

            for(int i = 0; i < listMeetingsFragment.getListToDisplay().size(); i++){
                Date currentDate = dateFormat.parse(listMeetingsFragment.getListToDisplay().get(i).getDate());

                assertTrue(currentDate.compareTo(startDate) >= 0 && currentDate.compareTo(endDate) <= 0);
            }
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
    }
}
