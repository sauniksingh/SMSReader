package com.saunik.smsreader.cache

import android.content.Context
import com.google.gson.Gson

class SharedPreferenceImpl(val context: Context) : SharedPreference {
    private val preferences = context.getSharedPreferences("sms_pref", Context.MODE_PRIVATE)
    private val gson by lazy { Gson() }

    override fun <T> getObject(key: String, clazz: Class<T>): T {
        val json = preferences.getString(key, null)
        return gson.fromJson(json, clazz)
    }

    override fun <T> putObject(key: String, value: T) {
        val json = gson.toJson(value)
        preferences.edit().putString(key, json).apply()
    }
}
