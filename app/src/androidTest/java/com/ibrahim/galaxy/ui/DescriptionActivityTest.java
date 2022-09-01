package com.ibrahim.galaxy.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.ibrahim.galaxy.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4ClassRunner.class)
public class DescriptionActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testContentDisplayed(){
        CountingIdlingResource componentIdlingResource = MainActivity.getIdlingResourceInTest();
        Espresso.registerIdlingResources(componentIdlingResource);

        onView(withId(R.id.recycler_images))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.desc_item_image)).check(matches(isDisplayed()));
        onView(withId(R.id.desc_button_back)).check(matches(isDisplayed()));
        onView(withId(R.id.desc_txt_title)).check(matches(isDisplayed()));
        onView(withId(R.id.desc_text_center)).check(matches(isDisplayed()));
        onView(withId(R.id.desc_txt_created)).check(matches(isDisplayed()));
        onView(withId(R.id.desc_txt_description)).check(matches(isDisplayed()));

        onView(withId(R.id.desc_button_back)).perform(click());

        //Tests if the back button navigated to main UI successfully
        onView(withId(R.id.top_app_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.recycler_images)).check(matches(isDisplayed()));

    }

}
