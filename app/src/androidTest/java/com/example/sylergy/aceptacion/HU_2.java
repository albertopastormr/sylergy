package com.example.sylergy.aceptacion;

import android.widget.EditText;

import com.example.sylergy.R;
import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.activities.utils.EspressoUtils;
import com.example.sylergy.activities.utils.ToastMatcher;
import com.example.sylergy.logs.Logs;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

public class HU_2 {

    private String productName;
    private String logMessage;
    private IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void emptyProductNameTest() {
        //Empty productName field
        productName = "";
        logMessage = Logs.NO_SEARCH_NAME;
        onView(allOf(withText("Searches"),isDescendantOfA(withId(R.id.bottomNavigationView)),
                isDisplayed())).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText(productName),
                pressImeActionButton());
        onView(withText(logMessage)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void ProductNotExists() throws InterruptedException {
        //Product doesn't exists
        productName = "xxxxx";
        logMessage = Logs.PRODUCT_NOT_FOUND;
        onView(allOf(withText("Searches"),isDescendantOfA(withId(R.id.bottomNavigationView)),
                isDisplayed())).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText(productName),
                pressImeActionButton());
        onView(withText(logMessage)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

    @Test
    public void correctProductName() {
        //Correct barcode and the product exists
        productName = "pechuga";

        onView(allOf(withText("Searches"),isDescendantOfA(withId(R.id.bottomNavigationView)),
                isDisplayed())).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText(productName),
                pressImeActionButton());

        onData(anything()).inAdapterView(withId(R.id.products_lv_list))
                .atPosition(0).perform(click());
        onView(withId(R.id.textViewProductName))
                .check(matches(EspressoUtils.isEditTextValueEqualTo("pechuga")));
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
