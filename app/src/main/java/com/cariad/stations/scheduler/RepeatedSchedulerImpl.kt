package com.cariad.stations.scheduler

import android.os.Handler

class RepeatedSchedulerImpl(private val handler: Handler,
                            private val intervalMs: Long,
                            private val task:() -> Unit): IRepeatedScheduler {

    override fun startRepeatedScheduler() {
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed(object: Runnable {
            override fun run() {
                task.invoke()
                handler.postDelayed(this, intervalMs)
            }
        }, intervalMs)
    }

    override fun stopRepeatedScheduler() {
        handler.removeCallbacksAndMessages(null)
    }
}