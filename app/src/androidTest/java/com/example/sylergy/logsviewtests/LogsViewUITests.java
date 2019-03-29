package com.example.sylergy.logsviewtests;

import android.view.View;

import com.example.sylergy.activities.testsactivities.TestViewLogsView;
import com.example.sylergy.logs.Logs;

import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class LogsViewUITests {

    @Rule
    public ActivityTestRule<TestViewLogsView> mActivityRule = new ActivityTestRule<>(TestViewLogsView.class);

    @Test
    public void correctShowLogWithException() {
        ViewInteraction btn = onView(withText("SHOW EXCEPTION"));
        btn.perform(click());

        //Check the toast
        onView(withText(Logs.UNKNOWN_ERROR)).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

    }

    @Test
    public void correctShowLogWithoutException() {
        ViewInteraction btn = onView(withText("SHOW NO EXCEPTION"));
        btn.perform(click());

        //Check the toast
        onView(withText(Logs.UNKNOWN_ERROR)).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

}
