package com.saunik.smsreader.data.repository

import com.saunik.smsreader.cache.SharedPreference
import com.saunik.smsreader.cache.SharedPreferenceImpl
import com.saunik.smsreader.data.api.ApiHelper
import com.saunik.smsreader.data.dao.SmsDao
import com.saunik.smsreader.data.model.Messages
import com.saunik.smsreader.data.model.Sms
import retrofit2.Response


/**
 * Created by Saunik Singh on 20/11/21.
 */
class SmsRepository(
    private val apiHelper: ApiHelper,
    private val smsDao: SmsDao,
    private val cache: SharedPreferenceImpl
) {
    suspend fun postSms(sms: Sms): Response<Sms> {
        smsDao.insertSms(sms)
        var messages: Messages? =
            cache.getObject(SharedPreference.KEY_MESSAGE, Messages::class.java)
        if (messages == null) {
            messages = Messages(ArrayList<Sms>())
        }
        messages.sms.add(sms)
        cache.putObject(SharedPreference.KEY_MESSAGE, messages)
        return apiHelper.postSms(sms)
    }

    suspend fun getDbSms() = smsDao.getAllSms()
    suspend fun getApiSms() = apiHelper.getSms()
    suspend fun getCacheSms(): ArrayList<Sms> {
        var messages: Messages? =
            cache.getObject(SharedPreference.KEY_MESSAGE, Messages::class.java)
        if (messages == null) {
            messages = Messages(arrayListOf())
        }
        return messages.sms
    }
}