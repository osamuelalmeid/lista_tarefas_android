package com.osamuelalmeida.myapplication.ui.addEditTask

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.osamuelalmeida.myapplication.R
import com.osamuelalmeida.myapplication.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class AddEditFragmentTest {

    @Before
    fun setUp() {
        launchFragmentInHiltContainer<AddEditFragment>(themeResId = R.style.AppTheme)
    }

    @Test
    fun checkIfAllFieldsAreDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_task_name)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.check_box_important)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.button_save_task)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}