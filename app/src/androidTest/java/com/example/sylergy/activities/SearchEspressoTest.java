package com.example.sylergy.activities;

import android.widget.EditText;

import com.example.sylergy.R;
import com.example.sylergy.activities.utils.EspressoUtils;
import com.example.sylergy.activities.utils.ToastMatcher;
import com.example.sylergy.logs.Logs;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchEspressoTest {
    private IdlingResource idlingResource;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void searchByNameUITest(){
        // search with products
        onView(allOf(withText("Searches"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText("Pechuga"),pressImeActionButton());

        onData(anything()).inAdapterView(withId(R.id.products_lv_list)).atPosition(0).perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(EspressoUtils.isEditTextValueEqualTo("Pechuga")));

        // search with a product not exist
        Espresso.pressBack();
        onView(isAssignableFrom(EditText.class)).perform(clearText());
        onView(isAssignableFrom(EditText.class)).perform(typeText("xxxxxxxxxx"),pressImeActionButton());
        onView(withText(Logs.PRODUCT_NOT_FOUND)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
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
