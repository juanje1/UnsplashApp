package com.unsplashapp

/**import android.view.View
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.unsplashapp.ui.activities.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep


class InstrumentationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun itemClickNavigatesToDetails() {
        sleep(7000)

        onView(withId(R.id.charactersList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.characterName)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.characterDescription)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.characterComics)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.comicsList)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.characterStories)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.storiesList)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.characterEvents)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.eventsList)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.characterSeries)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.seriesList)).check(matches(isAssignableFrom(TextView::class.java)))
    }

    @Test fun checkValuesSettings() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.settingsMenu)).perform(click())
        onView(withId(R.id.limitSeekBar)).check(matches(withProgress(25)))
        onView(withId(R.id.currentLimitTextView)).check(matches(withText("25")))
        onView(withId(R.id.orderSpinner)).check(matches(withValue("Name Ascending")))
    }

    private fun withProgress(expectedProgress: Int): Matcher<View> =
        object : BoundedMatcher<View, SeekBar>(SeekBar::class.java) {

            override fun matchesSafely(seekBar: SeekBar): Boolean =
                seekBar.progress == expectedProgress

            override fun describeTo(description: Description) {
                description.appendText("expected: ")
                description.appendText("$expectedProgress")
            }
        }

    private fun withValue(expectedValue: String): Matcher<View> =
        object : BoundedMatcher<View, Spinner>(Spinner::class.java) {

            override fun matchesSafely(spinner: Spinner): Boolean =
                spinner.selectedItem == expectedValue

            override fun describeTo(description: Description) {
                description.appendText("expected: ")
                description.appendText(expectedValue)
            }
        }
}**/