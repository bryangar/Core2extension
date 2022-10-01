package com.example.core2


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        onView(allOf(withId(R.id.spa))).perform(click())

        onView(allOf(withId(R.id.currentimage),
            withContentDescription("Picture"))).check(matches(isDisplayed()))

        onView(allOf(withId(R.id.country),
            withText("Spain"))).check(matches(isDisplayed()))

        onView(allOf(withId(R.id.place),
            withText("Madrid"))).check(matches(isDisplayed()))

        onView(allOf(withId(R.id.lastvisit),
            withText("25-03-2018"))).check(matches(isDisplayed()))
    }

    @Test
    fun mainActivityTest2() {

        onView(allOf(withId(R.id.spa))).perform(click())

        onView(
            allOf(withId(R.id.place),
                withText("Madrid"))).perform(ViewActions.replaceText("Mad"))

        onView(allOf(withId(R.id.place),
            withText("Mad"))).perform(ViewActions.closeSoftKeyboard())

        Espresso.pressBack()

        onView(allOf(withId(R.id.madrid),
            withText("Mad"))).check(matches(withText("Mad")))
    }

}
