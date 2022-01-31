package fr.example.mareu;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.notNullValue;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.PickerActions;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import fr.example.mareu.DI.DI;


public class AddMeetingTest {



    private int ITEM_MEETING_COUNT;
    @Before
    public void setUp() {
        ITEM_MEETING_COUNT = DI.getApiServiceMeetings().getMeetingList().size();
    }

    @Test
    public void checkInputSubject(){

        onView(withId(R.id.input_subject))
                .perform(click());

        onView(withText("Sujet Test"))
                .perform(click());

        Espresso.pressBack();
    }

    @Test
    public void checkIfClickOnParticipants() {

        onView(withId(R.id.button_selectedParticipant))
                .perform(click());

        onView(withId(R.id.list_participants_recycler))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkRoomSelected() {

        onView(withId(R.id.spinner_room))
                .perform(click());

        onView(withText(R.string.Yoshi))
                .perform(click());
    }

    @Test
    public void checkHourSelection() {

        onView(withId(R.id.input_time_button))
                .perform(click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10,30));

        onView(withText("OK"))
                .perform(click());
    }

    @Test
    public void checkDateSelection() {

        onView(withId(R.id.input_date_button))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 02, 15));

        onView(withText("OK"))
                .perform(click());
    }

    @Test
    public void checkIfAllDialogIsCompleted() {

        onView(withId(R.id.input_subject))
                .perform(click());

        Espresso.pressBack();

        onView(withId(R.id.button_selectedParticipant))
                .perform(click());

        Espresso.pressBack();

        onView(withId(R.id.spinner_room))
                .perform(click());

        Espresso.pressBack();

        onView(withId(R.id.input_time_button))
                .perform(click());

        Espresso.pressBack();

        onView(withId(R.id.input_date_button))
                .perform(click());

        Espresso.pressBack();
    }

    @Test
    public void CreateMeetingReunionTest() {
//SUJET
        onView(withId(R.id.input_subject))
                .perform(typeText("sujet test"));

        closeSoftKeyboard();

//PARTICIPANTS
        onView(withId(R.id.button_selectedParticipant))
                .perform(click());

        onView(withId(R.id.list_participants_recycler))
                .check(matches(isDisplayed()));
//SALLE
        onView(withId(R.id.spinner_room))
                .perform(click());


        onView(withText(R.string.Yoshi))
                .perform(click());
//DATE
        onView(withId(R.id.input_date_button))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 30));

        onView(withText("OK"))
                .perform(click());
//HEURE
        onView(withId(R.id.input_time_button))
                .perform(click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10,30));

        onView(withText("OK"))
                .perform(click());
//VALIDATION
        onView(withId(R.id.ok_button))
                .perform(click());
// CHECK AJOUT REUNION
//        onView(withId(R.id.recycler_meeting))
//                .check(???(ITEM_MEETING_COUNT+1));
    }
}
