package fr.example.mareu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.example.mareu.DI.DI;

@RunWith(AndroidJUnit4.class)

public class FilterTestUni {

    private FilterTestUni filterTestUni;

    @Before
    public void setupService() {
        filterTestUni = FilterTestUni.newInstance();
        //filterTestUni = DI.getApiServiceMeetings();
    }

    @Test
    public void filterRoomWithSuccess(){

        // Initialize
        filterTestUni.initList();

        // Specify a room filter selection
        filterTestUni.filterRooms();

        // Launch filter
        filterTestUni.createMeeting();

        // Check if filtered list
        assertEquals(3, filterTestUni.().size());
        for(int i = 0; i < filterTestUni.getListToDisplay().size(); i++){
            assertEquals("test", filterTestUni.getListToDisplay().get(i).getMeetingRoom());
        }
    }

    @Test
    public void filterDateWithSuccess(){

        // Initialize
        filterTestUni.initlIst();

        String filter = "30/01/2022";

        // Valid filter
       filterTestUni.toString(filter);

        // Check if every item date is equal or higher then filterDate
        assertEquals(2, filterTestUni.getListToDisplay().size());
        for(int i = 0; i < filterTestUni.getListToDisplay().size(); i++){
            assertTrue(filterTestUni.getListToDisplay().get(i).getDate().compareTo(filter) <= 0);
        }
    }
}
