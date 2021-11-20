package com.saunik.smsreader.cache

/**
 * Created by Saunik Singh on 20/11/21.
 */
interface SharedPreference {
    companion object {
        const val KEY_MESSAGE = "token"
    }

    fun <T> getObject(key: String, clazz: Class<T>): T
    fun <T> putObject(key: String, value: T)
}