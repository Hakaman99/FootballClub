package com.example.root.finalproject.match

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.root.finalproject.HomeActivity
import com.example.root.finalproject.R
import com.example.root.finalproject.R.id.*
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NextLeagueTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(ViewMatchers.withText("NEXT MATCH")).perform(ViewActions.click())
        Thread.sleep(7000)
        onView(Matchers.allOf(ViewMatchers.withId(recycler_view_team), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(Matchers.allOf(ViewMatchers.withId(recycler_view_team), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))
        onView(Matchers.allOf(ViewMatchers.withId(recycler_view_team), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))
    }

    @Test
    fun testDetailBehaviour() {
        onView(ViewMatchers.withText("NEXT MATCH")).perform(ViewActions.click())
        Thread.sleep(7000)
        onView(Matchers.allOf(ViewMatchers.withId(recycler_view_team), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        Thread.sleep(7000)
        onView(ViewMatchers.withId(date_events_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(date_events_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(ll_score_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(image_home_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(tv_image_home_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(image_home_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(score_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(image_away_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(tv_image_away_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(date_events_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(view_line_goals)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(home_goal_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_goal_details)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(view_line_goals)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(home_shots_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_shots_detail)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(view_line_up_goals)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(line_ups)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(home_line_up_goal_keeper)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_line_up_goal_keeper)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(home_line_up_defense)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_line_up_defense)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(home_lineup_substitutes)).perform(ViewActions.scrollTo())
        Thread.sleep(2000)

        onView(ViewMatchers.withId(home_line_up_midfield)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_lineup_midfield)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(home_lineup_forward)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_lineup_forward)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(home_lineup_substitutes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(away_lineup_substitutes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testTeamNextLeagueFavoriteBehaviour() {
        onView(ViewMatchers.withText("NEXT MATCH")).perform(ViewActions.click())
        Thread.sleep(7000)
        onView(Matchers.allOf(ViewMatchers.withId(recycler_view_team), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))
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
        onView(ViewMatchers.withText("MATCHES")).perform(ViewActions.click())
        onView(Matchers.allOf(ViewMatchers.withId(rv_favorite), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(Matchers.allOf(ViewMatchers.withId(rv_favorite), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(6000)

        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(withText("Removed to Favorite"))
                .inRoot(withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches(isDisplayed()))
    }

}