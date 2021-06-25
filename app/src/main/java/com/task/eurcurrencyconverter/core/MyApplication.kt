package com.spotspal.fanartec.core

import android.app.Application
import android.content.Context
import android.os.Build
import android.webkit.WebStorage
import android.webkit.WebView

class MyApplication : Application() {

    companion object {
        lateinit var appInstance: MyApplication
            private set

        @Synchronized
        fun getInstance(): Context? {
            return appInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (packageName != getProcessName()) {
                WebView.setDataDirectorySuffix(getProcessName())
            }
        }

        WebStorage.getInstance().deleteAllData()
        WebView(this).destroy()

    }


}