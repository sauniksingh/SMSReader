package com.saunik.smsreader.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Saunik Singh on 20/11/21.
 */
object DateUtil {
    @SuppressLint("SimpleDateFormat")
    fun getFormatedDate(timeStamp: Long): String {
        val formatterOut = SimpleDateFormat("EEE. dd/MM/yyyy hh:mm aa")
        try {
            return formatterOut.format(Date(timeStamp))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return timeStamp.toString()
    }
}