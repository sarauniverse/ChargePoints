package com.cariad.stations.scheduler

import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.activityScenarioRule
import com.cariad.stations.views.MainActivity
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Verifies the RepeatedSchedulerLifeCycleObserverImpl implementation with respect to the activity
 * lifecycle
 */
@RunWith(MockitoJUnitRunner::class)
class RepeatedSchedulerLifeCycleObserverTest {

    @get:Rule
    var mainActivityScenarioRule = activityScenarioRule<MainActivity>()

    @Mock
    private lateinit var repeatedScheduler: IRepeatedScheduler

    @Before
    fun setup() {
        val repeatedSchedulerLifeCycleObserver: IRepeatedSchedulerLifeCycleObserver =
            RepeatedSchedulerLifeCycleObserverImpl(repeatedScheduler)
        mainActivityScenarioRule.scenario.onActivity {
            it.lifecycle.addObserver(repeatedSchedulerLifeCycleObserver)
        }
    }

    /**
     * Verifies that the scheduler is started when the activity onCreate state is reached.
     */
    @Test
    fun test_OnCreate_LifeCycle() {
        verify(repeatedScheduler, times(1)).startRepeatedScheduler()
    }

    /**
     * Verify that the method stopRepeatedScheduler() is called 2 twice.
     * Once for onStop and another time for onDestroy
     */
    @Test
    fun test_OnDestroy_LifeCycle() {
        mainActivityScenarioRule.scenario.moveToState(Lifecycle.State.DESTROYED)
        verify(repeatedScheduler, times(2)).stopRepeatedScheduler()
    }

    /**
     * Verifies that the scheduler is restarted when the activity OnResume state is reached
     */
    @Test
    fun test_OnResume_LifeCycle() {
        mainActivityScenarioRule.scenario.moveToState(Lifecycle.State.RESUMED)
        verify(repeatedScheduler, times(1)).startRepeatedScheduler()
    }
}