package com.saunik.smsreader.data.repository

import android.util.Log
import com.saunik.smsreader.data.api.ApiHelper
import com.saunik.smsreader.data.dao.SmsDao
import com.saunik.smsreader.data.model.Sms
import retrofit2.Response


/**
 * Created by Saunik Singh on 20/11/21.
 */
class SmsRepository(
    private val apiHelper: ApiHelper, private val smsDao: SmsDao
) {
    suspend fun postSms(sms: Sms): Response<Sms> {
        Log.d("postSms", sms.toString())
//        val smsList = ArrayList<Sms>()
//        smsList.add(sms)
        smsDao.insertSms(sms)

        return apiHelper.postSms(sms)
    }

    suspend fun getDbSms() = smsDao.getAllSms()
    suspend fun getApiSms() = apiHelper.getSms()
}