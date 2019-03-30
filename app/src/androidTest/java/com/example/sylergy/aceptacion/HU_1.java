package com.example.sylergy.aceptacion;


import com.example.sylergy.R;
import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.activities.ProductActivity;

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
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HU_1 {

    String codigo;
    String mensajedeLog;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void barcodeTestVacio() throws InterruptedException {
        //numero vacio
        codigo  = "";
        mensajedeLog = "Please, introduce a barcode.";
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(codigo)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(withText(mensajedeLog)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void barcodeTestMalEscrito() throws InterruptedException {
        //numero con caracter no valido (caracter o letra)
        codigo = "eetwbtebtbe";
        mensajedeLog = "Product not found.";
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(codigo)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(withText(mensajedeLog)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void barcodeTestELCodigoNoSeEncuentraEnLaBaseDeDatos() throws InterruptedException {
        //numero correcto pero no se encuentra en la base de datos
        codigo = "2222222222222";
        mensajedeLog = "Product not found.";
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(codigo)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        onView(withText(mensajedeLog)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void barcodeTestTodoCorrecto() throws InterruptedException {
        //numero correcto y se encuentra en la base de datos
        codigo = "8480000592477";
        onView(ViewMatchers.withId(R.id.barcodeText)).check(matches(withText("")));
        onView(ViewMatchers.withId(R.id.barcodeText)).perform(replaceText(String.valueOf(codigo)));
        onView(ViewMatchers.withId(R.id.btnSearch)).perform(click());
        Thread.sleep(4000);
        onView(ViewMatchers.withId(R.id.textViewProductName)).check(matches(withText("PECHUGA DE PAVO, FINAS LONCHAS")));
    }
}