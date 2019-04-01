package com.example.sylergy.aceptacion;
import com.example.sylergy.R;
import com.example.sylergy.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HU_9 {
    String code = "8480000592477";


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void productTestShowAllCorrectly() throws InterruptedException {
        //numero correcto y se encuentra en la base de datos
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(code)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        Thread.sleep(4000);
        onView(ViewMatchers.withId(R.id.textViewProductName)).check(matches(not(withText(""))));
        onView(ViewMatchers.withId(R.id.imageViewProductImage)).check(matches(is(not(withText("")))));
    }

}
