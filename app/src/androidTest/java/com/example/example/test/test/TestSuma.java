package com.example.example.test.test;



import com.example.example.Activities.SumaActivity;
import com.example.example.R;

import org.junit.runner.RunWith;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.runner.AndroidJUnitRunner;
import cucumber.api.java.*;
import cucumber.api.java.en.*;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;
public class TestSuma {

    public ActivityTestRule<SumaActivity> sumaActivity = new ActivityTestRule<>(SumaActivity.class,false,false);

    /**
     * We launch the activity in Cucumber's {@link Before} hook.
     * See the notes above for the reasons why we are doing this.
     *
     * @throws Exception any possible Exception
     */
    @Before
    public void launchActivity() throws Exception {
        sumaActivity.launchActivity(null);
    }

    /**
     * All the clean up of application's data and state after each scenario must happen here
     */
    @After
    public void finishActivity() throws Exception {
        sumaActivity.finishActivity();
    }


    @Given("^Dos campos vacíos numéricos$")
    public void dosCamposVacíosNuméricos() throws Throwable {
        onView(ViewMatchers.withId(com.example.example.R.id.primerNumeroEt)).check(matches(withText("")));
        onView(ViewMatchers.withId(com.example.example.R.id.segundoNumeroEt)).check(matches(withText("")));
    }

    @When("^Se introducen \"([^\"]*)\" y \"([^\"]*)\"$")
    public void seIntroducenY(String a, String b) throws Throwable {
        onView(ViewMatchers.withId(com.example.example.R.id.primerNumeroEt)).perform(replaceText(String.valueOf(a)));
        onView(ViewMatchers.withId(com.example.example.R.id.segundoNumeroEt)).perform(replaceText(String.valueOf(b)));
    }

    @And("^y se pulsa enviar$")
    public void ySePulsaEnviar() throws Throwable {
        onView(ViewMatchers.withId(R.id.enviarBt)).perform(click());
    }


    @Then("^Se muestra el \"([^\"]*)\" de la suma en un diálogo$")
    public void seMuestraElResultadoDeLaSumaDeEnUnDiálogo(String resultado) throws Throwable {
        onView(withText(resultado)).inRoot(withDecorView(not(is(sumaActivity.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }
}
