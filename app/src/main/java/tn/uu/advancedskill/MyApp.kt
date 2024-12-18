package tn.uu.advancedskill

import tn.uu.baselibrary.base.BaseApplication

class MyApp: BaseApplication() {

    companion object {
        val instance: MyApp by lazy { BaseApplication.instance as MyApp }
    }

    override fun onCreate() {
        super.onCreate()

    }
}