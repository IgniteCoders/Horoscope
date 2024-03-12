package com.example.horoscope.utils

import android.app.Application
import android.content.Context


class ApplicationContextProvider : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        /**
         * Keeps a reference of the application context
         *
         * @return Returns the application context
         */
        var context: Context? = null
            private set
    }
}