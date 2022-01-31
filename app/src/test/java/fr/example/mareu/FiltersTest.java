package fr.example.mareu;

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

public class FiltersTest {

    private MainActivity mainActivity;

    @Test
    public void checkFilterDates() {

        // open menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click on "Filter by date"
        onView(withText(mainActivity.getString(R.id.trier_date)))
                .perform(click());

        // Click on TextInputEditText "Du"
        onView(withId(R.id.input_edit_filter_start_date))
                .perform(click());

        // Select start date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 28));

        // Confirm start date
        onView(withText("OK"))
                .perform(click());

        // Click on TextInputEditText "Au"
        onView(withId(R.id.input_edit_filter_end_date))
                .perform(click());

        // Select end date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 30));

        // Confirm start date
        onView(withText("OK"))
                .perform(click());

        // Confirm filter
        onView(withText("YES"))
                .perform(click());

        // Reset filter
        onView(withId(R.id.without_filter))
                .perform(click());
    }

    @Test
    public void checkFilterRooms() {

        // Open menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click on "Filter room"
        onView(withText(mainActivity.getString(R.id.trier_rooms)))
                .perform(click());

        // Selected rooms
        onView(withId(R.string.Mario))
                .perform(click());
        onView(withId(R.string.Luigi))
                .perform(click());
        onView(withId(R.string.Yoshi))
                .perform(click());

        // Confirm filter
        onView(withText("YES"))
                .perform(click());

        // Reset filter
        onView(withId(R.id.without_filter))
                .perform(click());
    }
}
