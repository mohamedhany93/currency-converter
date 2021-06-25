package com.spotspal.fanartec.network

import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesResponse
import retrofit2.Response
import retrofit2.http.*

interface CurrencyInterface {


    @GET("latest")
    suspend fun getCurrencyRates(@Query("access_key") accessKey:String ): Response<CurrencyRatesResponse>


}