package fr.example.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import fr.example.mareu.DI.DI;
import fr.example.mareu.model.DateHour;

public class AddMeetingTest {


    private MainActivity mActivity;
    private int ITEM_MEETING_COUNT;

    @Rule
    public final MeetingListAdapter<MainActivity> mMainActivityRule = new MeetingListAdapter(MeetingListAdapter);

    @Before
    public void setUp(){
        ITEM_MEETING_COUNT = DI.getApiServiceMeetings().getMeetingList().size();

        MainActivity mainActivity = mMainActivityRule.getItemId();
        ViewMatchers.assertThat(mainActivity, Matchers.notNullValue());
        mainActivity.onOptionsItemSelected(MainActivity.class(), mainActivity.getMenuInflater();
    }

    @Test
    public void CreateMeetingReunionTest() {

    }

    @Test
    public void checkRoomSelected(){

        onView(withId(R.id.spinner_room))
                .perform(click());

        onView(withText(R.string.Yoshi))
                .perform(click());

        Espresso.pressBack();
    }

    @Test
    public void checkDateSelected(){

        onView(withId(R.id.input_date_button))
                .perform(click());

        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(DatePicker.setmDate(15, 01, 2022));

        onView(withText("OK"))
                .perform(click());
    }

    @Test
    public void checkIf_HourSelection_updateHourTextInputEditText(){

        onView(withId(R.id.input_time_button))
                .perform(click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(DateHour.setmHour(10,30));

        onView(withText("OK"))
                .perform(click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(DatePicker.setmHour(11,30));

        onView(withText("OK"))
                .perform(click());
    }

    @Test
    public void checkIf_clickOnParticipantsTextInputEditText_displayListEmployeesFragment(){

        onView(withId(R.id.button_selectedParticipant))
                .perform(click());

        onView(withId(R.id.list_participants_recycler))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkButtonOk(){

        onView(withId(R.id.ok_button)).check(matches(not(isEnabled())))
         .perform(click());
    }

    @Test
    public void checkIfAllDialogIsCompleted(){

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
}
