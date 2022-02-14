package fr.example.mareu;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static fr.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FiltersTest {

    private MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

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

        // Select start date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 28));

        // Confirm start date
        onView(withText("OK"))
                .perform(click());

        // Click on TextInputEditText "Au"
        onView(withId(R.id.input_filter_date_end))
                .perform(click());

        // Select end date
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 01, 30));

        // Confirm start date
        onView(withText("OK"))
                .perform(click());

        // Confirm filter
        onView(withText("VALIDER"))
                .perform(click());
    }

    @Test
    public void checkFilterRooms() {
        onView(withId(R.id.recycler_meeting)).check(withItemCount(4));

        // Open menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        // Click on "Filter room"
        onView(anyOf(withText(R.string.room_filter), withId(R.id.trier_rooms)))
                .perform(click());

        // Selected rooms
        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(0);
        appCompatCheckedTextView.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(2);
        appCompatCheckedTextView2.perform(click());

        // Confirm filter
        onView(withText("VALIDER"))
                .perform(click());

        onView(withId(R.id.recycler_meeting)).check(withItemCount(2));
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
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

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position" + position + "in parent");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
}
