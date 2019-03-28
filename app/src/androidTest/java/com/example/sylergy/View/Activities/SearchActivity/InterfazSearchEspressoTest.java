package com.example.sylergy.View.Activities.SearchActivity;

import com.example.sylergy.activities.BarcodeProductFragment;
import com.example.sylergy.R;
import com.example.sylergy.logs.Logs;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class InterfazSearchEspressoTest {

    @Rule
    public ActivityTestRule<BarcodeProductFragment> mActivityRule = new ActivityTestRule<>(BarcodeProductFragment.class);

    @Test
    public void InterfazUITest() throws InterruptedException {
        ViewInteraction btnSearch = onView(withText("SEARCH"));

        //test with void bar code
        onView(withId(R.id.barcodeText)).perform(clearText());
        btnSearch.perform(click());
        onView(withText(Logs.NO_BARCODE)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        //test with bar code number
        onView(withId(R.id.barcodeText)).perform(typeText("123456"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withText(Logs.PRODUCT_NOT_FOUND)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }



}
