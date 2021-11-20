package com.saunik.smsreader.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saunik.smsreader.data.model.Sms

/**
 * Created by Saunik Singh on 20/11/21.
 */
class SmsConverter {
    @TypeConverter
    fun stringToSms(json: String): Sms {
        val gson = Gson()
        val type = object : TypeToken<Sms>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun SmsToString(list: Sms): String {
        val gson = Gson()
        val type = object : TypeToken<Sms>() {}.type
        return gson.toJson(list, type)
    }
}