package com.cariad.stations.scheduler

import android.util.Log
import androidx.lifecycle.LifecycleOwner

class RepeatedSchedulerLifeCycleObserverImpl(
    private val repeatedScheduler: IRepeatedScheduler) : IRepeatedSchedulerLifeCycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.i("HandlerLifeCycleObserver", "onCreate()")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i("HandlerLifeCycleObserver", "onStart()")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i("HandlerLifeCycleObserver", "onResume()")
        repeatedScheduler.startRepeatedScheduler()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i("HandlerLifeCycleObserver", "onPause()")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i("HandlerLifeCycleObserver", "onStop()")
        repeatedScheduler.stopRepeatedScheduler()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i("HandlerLifeCycleObserver", "onDestroy()")
        repeatedScheduler.stopRepeatedScheduler()
    }
}