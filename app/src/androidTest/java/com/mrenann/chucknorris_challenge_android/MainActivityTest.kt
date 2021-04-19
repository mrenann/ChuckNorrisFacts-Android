package com.mrenann.chucknorris_challenge_android

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mrenann.chucknorris_challenge_android.view.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @get: Rule
    val activitySRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isActivityIsVisible() {
           onView(withId(R.id.main_act)).check(matches(isDisplayed()))
        }

    @Test
    fun viewsisDisplayed() {
        onView(withId(R.id.searchV)).check(matches(isDisplayed()))
        onView(withId(R.id.infoImg)).check(matches(isDisplayed()))
        onView(withId(R.id.infoTxt)).check(matches(isDisplayed()))
    }

}