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

        /*// Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_new_product), withContentDescription("New Product"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottomNavigationView),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

       /* ViewInteraction textView = onView(
                allOf(withId(R.id.createProductTitle), withText("Create product"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Create product"))); */

      /* ViewInteraction textView = onView(allOf(withId(R.id.createProductTitle)));
       textView.check(matches(withText("Create product")));

        ViewInteraction imageView = onView(allOf(withId(R.id.productImage)));
        imageView.check(matches(isDisplayed()));

        ViewInteraction editText = onView(allOf(withId(R.id.editText_productName)));


        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.editText_productBarcode)));
        editText2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.button_uploadPicture)));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button_scanBarcode)));
        button2.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.nutrimentsTitle)));
        textView2.check(matches(withText("Nutrients:")));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.button_addNutrients)));
        button3.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.selectedNutrients)));
        textView3.check(matches(withText("")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.titleIngredients)));
        textView4.check(matches(withText("Ingredients:")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.ingredients)));
        editText3.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }

    /*private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }*/
}
