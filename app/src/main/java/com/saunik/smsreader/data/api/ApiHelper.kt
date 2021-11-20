package com.saunik.smsreader.data.api

import com.saunik.smsreader.data.model.Sms
import retrofit2.Response

/**
 * Created by Saunik Singh on 20/11/21.
 */
interface ApiHelper {
    suspend fun getSms(): Response<List<Sms>>
    suspend fun postSms(sms: Sms): Response<Sms>
}