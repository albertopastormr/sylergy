package com.example.sylergy.activities;

import com.example.sylergy.R;

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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityCameraEspressoTest {
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void cameraButtonUITest(){
        onView(withId(R.id.btnCamera)).perform(click());
        Espresso.pressBack();
        onView(allOf(withText("Home"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed()));

    }

    // Test camera active

    /**@Test
    public void cameraCorrectBarcodeUITest() throws InterruptedException {
        onView(allOf(withText("Barcode"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        intended(hasAction(equalTo(MediaStore.ACTION_IMAGE_CAPTURE)));
    }*/

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
