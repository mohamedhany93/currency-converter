package com.task.eurcurrencyconverter.repository

import com.spotspal.fanartec.core.BaseResponse
import com.spotspal.fanartec.network.ApiClient
import com.spotspal.fanartec.network.CurrencyInterface
import com.spotspal.fanartec.network.NetworkUtils.FIXER_KEY
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesResponse
import network.SafeApiRequest

class CurrencyRepository : SafeApiRequest() {

    val apiService = ApiClient().client.create(CurrencyInterface::class.java)


    suspend fun getCurrencyRates(
    ): CurrencyRatesResponse{
        return apiRequest{ apiService.getCurrencyRates(FIXER_KEY)}
    }

}