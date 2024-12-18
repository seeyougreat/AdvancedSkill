package tn.uu.advancedskill.example.delegate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NeedFunctionActivity :
    AppCompatActivity(),
    AnalyticsDelegate by AnalyticsDelegateImpl(),
    LogoutDelegate by LogoutDelegateImp()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerAnalytics(lifecycle)
        registerLogout(this)
    }
}