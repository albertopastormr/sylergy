package com.example.sylergy.activities;


import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.sylergy.R;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ActivityCreateProductEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void activityCreateProductEspressoTest() {
        onView(CoreMatchers.allOf(withText("New Product"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.createProductTitle))).check(matches(withText("Create product")));
        onView(allOf(withId(R.id.productImage))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.editText_productName))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.editText_productBarcode))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button_uploadPicture))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.button_scanBarcode))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.nutrimentsTitle))).check(matches(withText("Nutrients:")));
        onView(allOf(withId(R.id.button_addNutrients))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.selectedNutrients))).check(matches(withText("")));
        onView(allOf(withId(R.id.titleIngredients))).check(matches(withText("Ingredients:")));
        onView(allOf(withId(R.id.ingredients))).perform(scrollTo()).check(matches(isDisplayed()));
    }
}
