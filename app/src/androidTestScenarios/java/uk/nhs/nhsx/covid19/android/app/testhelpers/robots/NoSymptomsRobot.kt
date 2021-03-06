package uk.nhs.nhsx.covid19.android.app.testhelpers.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import uk.nhs.nhsx.covid19.android.app.R

class NoSymptomsRobot {

    fun confirmNoSymptomsScreenIsDisplayed() {
        onView(withId(R.id.titleNoSymptoms))
            .check(matches(isDisplayed()))
    }
}
