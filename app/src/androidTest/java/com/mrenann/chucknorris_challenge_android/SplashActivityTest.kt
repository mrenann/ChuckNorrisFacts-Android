package com.mrenann.chucknorris_challenge_android

import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.mrenann.chucknorris_challenge_android.view.ui.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

@RunWith(AndroidJUnit4ClassRunner::class)
class SplashActivityTest{

    @get: Rule
    val activitySRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun isActivityIsVisible() {
        onView(withId(R.id.splash_act)).check(matches(isDisplayed()))
    }

}