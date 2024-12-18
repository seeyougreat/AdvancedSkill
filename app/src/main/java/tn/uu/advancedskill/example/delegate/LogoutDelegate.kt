package tn.uu.advancedskill.example.delegate

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


interface LogoutDelegate {
    fun registerLogout(activity: AppCompatActivity)
}

class LogoutDelegateImp: LogoutDelegate, DefaultLifecycleObserver {
    private lateinit var activity: AppCompatActivity

    override fun registerLogout(activity: AppCompatActivity) {
        this.activity = activity
        this.activity.lifecycle.addObserver(this)
    }

    private lateinit var logoutReceiver: BroadcastReceiver

    override fun onCreate(owner: LifecycleOwner) {

        logoutReceiver = LogoutReceiver()

        activity.registerReceiver(logoutReceiver, IntentFilter("ACTION_LOGOUT"))

    }

    inner class LogoutReceiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            activity.finish()
        }

    }


}
