package tn.uu.advancedskill.example.delegate

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

interface AnalyticsDelegate {
    fun registerAnalytics(lifecycle: Lifecycle)
}

class AnalyticsDelegateImpl: AnalyticsDelegate, DefaultLifecycleObserver {
    override fun registerAnalytics(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    override fun onStart(owner: LifecycleOwner) {
        traceEvent("Activity started")
    }

    override fun onStop(owner: LifecycleOwner) {
        traceEvent("Activity stop")
    }

    private fun traceEvent(event: String) {
        Log.e("xxx", "trace event: $event")
    }


}