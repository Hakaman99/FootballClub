package com.example.root.finalproject.Team

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.root.finalproject.HomeActivity
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamsTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(2000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(teams)).perform(click())
        Thread.sleep(7000)
        onView(withId(list_event)).check(matches(isDisplayed()))
        onView(withId(list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }

    @Test
    fun testPlayerBehaviour() {
        Thread.sleep(2000)
        onView(withId(teams)).perform(click())
        Thread.sleep(7000)
        onView(withId(list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(7000)
        onView(ViewMatchers.withText("PLAYERS")).perform(ViewActions.click())
        Thread.sleep(7000)
        onView(withId(rv_players)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(withId(rv_players)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Test
    fun testDetailTeamBehaviour() {
        Thread.sleep(2000)
        onView(withId(teams)).perform(click())
        Thread.sleep(7000)
        onView(withId(list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(7000)
        onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Added to Favorite"))
                .inRoot(RootMatchers.withDecorView(Matchers.not(activityRule.activity.window.decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(4000)

        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Removed to Favorite"))
                .inRoot(RootMatchers.withDecorView(Matchers.not(activityRule.activity.window.decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(4000)

        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Added to Favorite"))
                .inRoot(RootMatchers.withDecorView(Matchers.not(activityRule.activity.window.decorView)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(4000)
        onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description)).perform(ViewActions.click())
        onView(ViewMatchers.withId(bottom_navigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(favorites)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(ViewMatchers.withText("TEAMS")).perform(ViewActions.click())
        onView(Matchers.allOf(ViewMatchers.withId(rv_favorite), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(Matchers.allOf(ViewMatchers.withId(rv_favorite), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(6000)
        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Removed to Favorite"))
                .inRoot(RootMatchers.withDecorView(CoreMatchers.not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description)).perform(ViewActions.click())
    }

}