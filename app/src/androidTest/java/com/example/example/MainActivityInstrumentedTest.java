package com.example.example;

import com.example.example.Activities.MainActivity;
import com.example.example.Activities.ProductActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentedTest {
    private IdlingResource idlingResource;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void MainUITest(){
        ViewInteraction btnSearch = onView(withText("SEARCH"));

        // test with void bar code
        btnSearch.perform(click());
        onView(withText("You have to set a barcode")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        // test with bar code doesn't exist
        onView(withId(R.id.barcode_text)).perform(typeText("88888888"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withText("The product doesn't exist")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        // test with a exist bar code
        onView(withId(R.id.barcode_text)).perform(clearText()).perform(typeText("12345"),closeSoftKeyboard());
        Intents.init();
        btnSearch.perform(click());
        intended(hasComponent(ProductActivity.class.getName()));

    }
    @Before
    public void registerActivity(){
        idlingResource = mActivityRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }
    @After
    public void unregisterActivity(){
        IdlingRegistry.getInstance().unregister(idlingResource);
    }


}
