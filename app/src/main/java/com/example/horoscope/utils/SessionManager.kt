package com.example.horoscope.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context:Context) {

    companion object {
        const val FAVORITE_HOROSCOPE = "FAVORITE_HOROSCOPE"
    }

    private var sharedPref:SharedPreferences? = null

    init {
        sharedPref = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
    }

    fun setFavoriteHoroscope (id:String) {
        val editor = sharedPref?.edit()
        if (editor != null) {
            editor.putString(FAVORITE_HOROSCOPE, id)
            editor.apply()
        }
    }

    fun getFavoriteHoroscope ():String? {
        return sharedPref?.getString(FAVORITE_HOROSCOPE, null)
    }
}