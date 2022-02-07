package fr.example.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import android.view.View;
import android.widget.DatePicker;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.internal.platform.app.ActivityLifecycleTimeout;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FiltersTest {

    private MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mainActivity = mActivityRule.getActivity();
        assertThat(mainActivity, notNullValue());
    }

    @Test
    public void checkFilterDates() {

        // open menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click on "Filter by date"
       onView(anyOf(withText(R.string.date_filter), withId(R.id.trier_date)))
               .perform(click());

        // Click on TextInputEditText "Du"
        onView(withId(R.id.input_filter_date_start))
                .perform(click());

        onView(isRoot()).perform(waitFor(2000));

        // Select start date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 28));

        // Confirm start date
        onView(withText("OK"))
                .perform(click());

        // Click on TextInputEditText "Au"
        onView(withId(R.id.input_filter_date_end))
                .perform(click());

        onView(isRoot()).perform(waitFor(2000));

        // Select end date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 30));

        // Confirm start date
        onView(withText("OK"))
                .perform(click());

        // Confirm filter
        onView(withText("VALIDER"))
                .perform(click());

        // CHECK NOMBRES DELEMENTS DANS LA LISTE
        // Reset filter
//        onView(withId(R.id.without_filter))
//                .perform(click());
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

    public static ViewAction waitFor (final long millis) {
        return  new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for" + millis + "milliseconds.";
            }

            @Override
            public void perform(UiController uiController, View view) {

                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}
