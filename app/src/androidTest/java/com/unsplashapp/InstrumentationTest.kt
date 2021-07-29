package com.unsplashapp

import android.view.View
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

        onView(withId(R.id.photosList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.userNameText)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.photoDescription)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.photoLikes)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.photoCamera)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.make)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.model)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.exposureTime)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.aperture)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.focalLength)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.iso)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.photoLocation)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.city)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.country)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.photoTags)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.tagsList)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.photoUser)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.name)).check(matches(isAssignableFrom(TextView::class.java)))
        onView(withId(R.id.userName)).check(matches(isAssignableFrom(TextView::class.java)))
    }

    @Test fun checkValuesSettings() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.settingsMenu)).perform(click())
        onView(withId(R.id.numberOfPageEditText)).check(matches(withText("1")))
        onView(withId(R.id.numberPerPageEditText)).check(matches(withText("10")))
        onView(withId(R.id.orderSpinner)).check(matches("Latest".withValue()))
    }

    private fun String.withValue(): Matcher<View> =
        object : BoundedMatcher<View, Spinner>(Spinner::class.java) {

            override fun matchesSafely(spinner: Spinner): Boolean =
                spinner.selectedItem == this@withValue

            override fun describeTo(description: Description) {
                description.appendText("expected: ")
                description.appendText(this@withValue)
            }
        }
}