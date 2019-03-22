package com.example.sylergy.picassotests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.sylergy.activities.testsviews.TestViewPicasso;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class checks the functionality of Picasso of insert images into views. We are using Picasso in our project the same way as the Activity TestViewPicasso does,
 * which we are using to check the tests
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PicassoIntegrationTests {

    @Rule
    public ActivityTestRule<TestViewPicasso> mActivityRule = new ActivityTestRule<>(TestViewPicasso.class);

    @Test
    public void correctSetImageTest() {
        ViewInteraction btn = onView(withText("CHANGE IMAGE"));
        btn.perform(click());

        TestViewPicasso act = mActivityRule.getActivity();
        assertTrue(act.isCustomImage);
    }

    @Test
    public void incorrectSetImageTest() {
        ViewInteraction btn = onView(withText("INCORRECT"));
        btn.perform(click());

        TestViewPicasso act = mActivityRule.getActivity();
        assertTrue(act.isIncorrect);
        assertFalse(act.isCustomImage);
    }

}
