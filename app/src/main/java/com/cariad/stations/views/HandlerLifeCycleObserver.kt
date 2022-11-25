package com.cariad.stations.views

import android.os.Handler
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class HandlerLifeCycleObserver(private val handler: Handler,
                               private val intervalMs: Long,
                               private val task:() -> Unit,): DefaultLifecycleObserver {
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
        startRepeatedScheduler()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i("HandlerLifeCycleObserver", "onPause()")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i("HandlerLifeCycleObserver", "onStop()")
        stopRepeatedScheduler()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i("HandlerLifeCycleObserver", "onDestroy()")
        stopRepeatedScheduler()
    }

    private fun startRepeatedScheduler() {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(object: Runnable {
            override fun run() {
                task.invoke()
                handler.postDelayed(this, intervalMs)
            }
        }, intervalMs)
    }

    private fun stopRepeatedScheduler() {
        handler.removeCallbacksAndMessages(null)
    }
}