package com.example.sylergy.activities;

import com.example.sylergy.activities.utils.EspressoUtils;
import com.example.sylergy.activities.utils.ToastMatcher;
import com.example.sylergy.R;
import com.example.sylergy.logs.Logs;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivitySearchEspressoTest {
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void searchByBarCodeUITest() throws InterruptedException {
        onView(allOf(withText("Home"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        ViewInteraction btnSearch = onView(withText("SEARCH"));

        //test with a exist bar code
        onView(withId(R.id.barcodeText)).perform(typeText("8480000592477"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withId(R.id.textViewProductName)).check(matches(EspressoUtils.isEditTextValueEqualTo("Pechuga")));

        //test with void bar code
        Espresso.pressBack();
        onView(withId(R.id.barcodeText)).perform(clearText());
        btnSearch.perform(click());
        onView(withText(Logs.NO_BARCODE)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));

        Thread.sleep(3000);

        //test with not exist bar code
        onView(withId(R.id.barcodeText)).perform(clearText());
        onView(withId(R.id.barcodeText)).perform(typeText("1234"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withText(Logs.PRODUCT_NOT_FOUND)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void nutrientsTest(){
        onView(allOf(withText("Home"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        ViewInteraction btnSearch = onView(withText("SEARCH"));

        //product activity have nutrients text view
        onView(withId(R.id.barcodeText)).perform(typeText("8480000592477"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withText(R.string.nutrients_label)).check(matches(isEnabled()));
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

    @Test
    public void ingredientTest(){
        onView(allOf(withText("Home"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        ViewInteraction btnSearch = onView(withText("SEARCH"));

        //product activity have nutrients text view
        onView(withId(R.id.barcodeText)).perform(typeText("8480000592477"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withText(R.string.ingredients_label)).check(matches(isEnabled()));
    }

    @Test
    public void ingredientFlowTest(){
        onView(allOf(withText("Home"),isDescendantOfA(withId(R.id.bottomNavigationView)),isDisplayed())).perform(click());
        ViewInteraction btnSearch = onView(withText("SEARCH"));

        //product activity have nutrients text view
        onView(withId(R.id.barcodeText)).perform(typeText("8480000592477"),closeSoftKeyboard());
        btnSearch.perform(click());
        onView(withId(R.id.flowlayoutIngredientTag)).check(matches(isEnabled()));
    }
}
