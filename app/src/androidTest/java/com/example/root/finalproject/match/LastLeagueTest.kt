package com.example.root.finalproject.match

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.root.finalproject.HomeActivity
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LastLeagueTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(7000)
        onView(allOf(withId(recycler_view_team), isDisplayed())).check(matches(isDisplayed()))
        onView(allOf(withId(recycler_view_team), isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(allOf(withId(recycler_view_team), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
    }

    @Test
    fun testDetailBehaviour() {
        Thread.sleep(7000)
        onView(allOf(withId(recycler_view_team), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(7000)
        onView(withId(date_events_detail)).check(matches(isDisplayed()))
        onView(withId(date_events_detail)).check(matches(isDisplayed()))
        onView(withId(ll_score_detail)).check(matches(isDisplayed()))
        onView(withId(image_home_detail)).check(matches(isDisplayed()))
        onView(withId(tv_image_home_detail)).check(matches(isDisplayed()))
        onView(withId(image_home_detail)).check(matches(isDisplayed()))
        onView(withId(score_detail)).check(matches(isDisplayed()))
        onView(withId(image_away_detail)).check(matches(isDisplayed()))
        onView(withId(tv_image_away_detail)).check(matches(isDisplayed()))
        onView(withId(date_events_detail)).check(matches(isDisplayed()))
        onView(withId(view_line_goals)).check(matches(isDisplayed()))
        onView(withId(home_goal_details)).check(matches(isDisplayed()))
        onView(withId(away_goal_details)).check(matches(isDisplayed()))
        onView(withId(view_line_goals)).check(matches(isDisplayed()))
        onView(withId(home_shots_detail)).check(matches(isDisplayed()))
        onView(withId(away_shots_detail)).check(matches(isDisplayed()))
        onView(withId(view_line_up_goals)).check(matches(isDisplayed()))
        onView(withId(line_ups)).check(matches(isDisplayed()))
        onView(withId(home_line_up_goal_keeper)).check(matches(isDisplayed()))
        onView(withId(away_line_up_goal_keeper)).check(matches(isDisplayed()))
        onView(withId(home_line_up_defense)).check(matches(isDisplayed()))
        onView(withId(away_line_up_defense)).check(matches(isDisplayed()))

        onView(withId(home_lineup_substitutes)).perform(scrollTo())
        Thread.sleep(2000)

        onView(withId(home_line_up_midfield)).check(matches(isDisplayed()))
        onView(withId(away_lineup_midfield)).check(matches(isDisplayed()))
        onView(withId(home_lineup_forward)).check(matches(isDisplayed()))
        onView(withId(away_lineup_forward)).check(matches(isDisplayed()))
        onView(withId(home_lineup_substitutes)).check(matches(isDisplayed()))
        onView(withId(away_lineup_substitutes)).check(matches(isDisplayed()))
    }

    @Test
    fun testTeamLastLeagueFavoriteBehaviour() {
        Thread.sleep(7000)
        onView(allOf(withId(recycler_view_team), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(7000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to Favorite"))
                .inRoot(withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        Thread.sleep(4000)

        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to Favorite"))
                .inRoot(withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        Thread.sleep(4000)

        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to Favorite"))
                .inRoot(withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
        Thread.sleep(4000)

        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        Thread.sleep(2000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        Thread.sleep(2000)
        onView(withText("MATCHES")).perform(click())
        onView(allOf(withId(rv_favorite), isDisplayed())).check(matches(isDisplayed()))
        onView(allOf(withId(rv_favorite), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(6000)

        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed to Favorite"))
                .inRoot(withDecorView(CoreMatchers.not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
    }


}