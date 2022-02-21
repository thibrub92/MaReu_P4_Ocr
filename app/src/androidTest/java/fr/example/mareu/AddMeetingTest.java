package fr.example.mareu;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import fr.example.mareu.DI.DI;
import fr.example.mareu.service.ApiServiceMeetings;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddMeetingTest {

    private ApiServiceMeetings apiServiceMeetings;
    private int itemCount;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        apiServiceMeetings = DI.getApiServiceMeetings();
        itemCount = apiServiceMeetings.getMeetingList().size();
    }

    @Test
    public void addMeetingTest() {
        // Go add meeting view
        onView(withId(R.id.add_meeting)).perform(click());

        // Set subject
        onView(withId(R.id.input_subject)).perform(replaceText("sujet test "), closeSoftKeyboard());

        //Selected participants
        onView(withId(R.id.button_selectedParticipant)).perform(click());

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
                .atPosition(1);
        appCompatCheckedTextView2.perform(click());

        onView(withText("OK")).perform(scrollTo(), click());

        // Selected rooms
        onView(withId(R.id.spinner_room)).perform(click());

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        materialTextView.perform(click());

        // Selected time
        onView(withId(R.id.input_time_button)).perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        // Selected date
        onView(withId(R.id.input_date_button)).perform(click());

        ViewInteraction materialTextView1 = onView(
                allOf(withClassName(is("com.google.android.material.textview.MaterialTextView")), withText("2022"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView1.perform(click());

        DataInteraction materialTextView3 = onData(anything())
                .inAdapterView(allOf(withClassName(is("android.widget.YearPickerView")),
                        childAtPosition(
                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                1)))
                .atPosition(123);
        materialTextView3.perform(scrollTo(), click());

        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(scrollTo(), click());

        // valid add meeting
        onView(withId(R.id.ok_button)).perform(click());

        // Check add meeting into the list
        assertEquals(itemCount + 1, apiServiceMeetings.getMeetingList().size());
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
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
