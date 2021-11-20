package com.saunik.smsreader.data.api

import com.saunik.smsreader.data.model.Sms
import retrofit2.Response

/**
 * Created by Saunik Singh on 20/11/21.
 */
class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getSms(): Response<List<Sms>> = apiService.getSms()
    override suspend fun postSms(sms: Sms): Response<Sms> = apiService.sendSms(sms)
}