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


    private MainActivity mActivity;
    private int ITEM_MEETING_COUNT;
    @Before
    public void setUp() {
        ITEM_MEETING_COUNT = DI.getApiServiceMeetings().getMeetingList().size();

        MainActivity mainActivity =  mActivity.onOptionsItemSelected();
        assertThat(mainActivity, notNullValue());
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

        Espresso.pressBack();
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

        onView(withId(R.id.input_subject))
                .perform(typeText("sujet test"));

        closeSoftKeyboard();

        onView(withId(R.id.spinner_room))
                .perform(click());


        onView(withText(R.string.room_1))
                .perform(click());

        // Click on "Date selection" TextInputEditText
        onView(withId(R.id.text_input_date))
                .perform(click());

        // Select date 25/11/2020 on DatePicker
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2020, 11, 30));

        // Confirm start date (close DatePickerDialog)
        onView(withText("OK"))
                .perform(click());

        // Click on "Start hour selection" TextInputEditText
        onView(withId(R.id.text_input_hour_start))
                .perform(click());

        // Select start hour (H24 format) : 10h30
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10,30));

        // Confirm selection
        onView(withText("OK"))
                .perform(click());


    }
}
