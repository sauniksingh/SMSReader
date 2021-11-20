package com.saunik.smsreader.data.api

import com.saunik.smsreader.data.model.Sms
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Saunik Singh on 20/11/21.
 */
interface ApiService {
    @GET("/sms")
    suspend fun getSms(): Response<List<Sms>>

    @POST("/sms")
    suspend fun sendSms(@Body sms: Sms): Response<Sms>
}