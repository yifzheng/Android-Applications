package com.example.firebaseapp;


import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.intent.Intents.*;
import static androidx.test.espresso.intent.matcher.IntentMatchers.*;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    // test to check if all the necessary UI elements are rendered
    @Test
    public void A_UIElementsAreDisplayed(){
        onView(withId(R.id.login_email)).check(matches(isDisplayed()));
        onView(withId(R.id.login_password)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        onView(withId(R.id.register_here)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_login)).check(matches(withText("Login")));
    }
    // test to check whether the fields entered are correct
    @Test
    public void B_fieldsInputTest() {
        onView(withId(R.id.login_email)).perform(typeText("johndoe@gmail.com"));
        String expected = "johndoe@gmail.com";
        onView(withId(R.id.login_password)).perform(typeText("password"));
        String passwordExpected = "password";
        onView(withId(R.id.login_email)).check(matches(withText(expected)));
        onView(withId(R.id.login_password)).check(matches(withText(passwordExpected)));
    }
    // Test to check for intent transition to Register Activity
    @Test
    public void C_moveToRegisterActivity(){
        Intents.init();
        onView(withId(R.id.register_here)).perform(click());
        intended(hasComponent("com.example.firebaseapp.RegisterActivity"));
    }
}
