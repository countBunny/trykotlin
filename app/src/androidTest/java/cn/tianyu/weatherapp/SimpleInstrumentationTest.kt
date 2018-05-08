package cn.tianyu.weatherapp

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import cn.tianyu.weatherapp.ui.activity.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun modifyZipCode_changesToolbarTitle(){
        openContextualActionModeOverflowMenu()
        onView(withText(R.string.settings)).perform(click())
        onView(withId(R.id.cityName)).perform(replaceText("qingdao"))
        pressBack()
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToobarTitle(`is`("qingdao"))))
    }

    private fun withToobarTitle(textMatcher: Matcher<CharSequence>):
            Matcher<Any> = object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java){
        override fun matchesSafely(item: Toolbar?): Boolean {
            return textMatcher.matches(item?.title.toString())
        }

        override fun describeTo(description: Description?) {
            description?.appendText("with toolbar title: ")
            textMatcher.describeTo(description)
        }
    }
}