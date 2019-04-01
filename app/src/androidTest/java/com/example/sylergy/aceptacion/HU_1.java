package com.example.sylergy.aceptacion;


import com.example.sylergy.R;
import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.activities.utils.ToastMatcher;
import com.example.sylergy.logs.Logs;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class HU_1 {

    private String code;
    private String logMessage;
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void emptyBarcodeTest() throws InterruptedException {
        //Empty barcode field
        code = "";
        logMessage = Logs.NO_BARCODE;
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(code)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(withText(logMessage)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void badBarcodeTest() throws InterruptedException {
        //Invalid barcode format
        code = "eetwbtebtbe";
        logMessage = Logs.PRODUCT_NOT_FOUND;
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(code)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(withText(logMessage)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void correctBarcodeAndProductNoExists() throws InterruptedException {
        //Correct barcode but the product doesn't exists
        code = "-1";
        logMessage = Logs.PRODUCT_NOT_FOUND;
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(code)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(withText(logMessage)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void correctBarcodeAndProductFoundTest() throws InterruptedException {
        //Correct barcode and the product exists
        code = "8480000592477";
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(code)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(ViewMatchers.withId(R.id.textViewProductName)).check(matches(
                withText("PECHUGA DE PAVO, FINAS LONCHAS")));
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