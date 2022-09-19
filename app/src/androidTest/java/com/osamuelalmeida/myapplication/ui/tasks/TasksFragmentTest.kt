package com.osamuelalmeida.myapplication.ui.tasks

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.osamuelalmeida.myapplication.R
import com.osamuelalmeida.myapplication.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class TasksFragmentTest {

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<TasksFragment>(themeResId = R.style.AppTheme)
    }

    @Test
    fun checkIfAllFieldsAreDisplayed() {
        onView(ViewMatchers.withId(R.id.recycler_view_tasks)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.button_add_task)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}