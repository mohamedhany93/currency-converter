package com.task.eurcurrencyconverter.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.spotspal.fanartec.core.MyApplication
import com.spotspal.fanartec.network.ApiClient
import com.spotspal.fanartec.network.Coroutines
import com.spotspal.fanartec.network.CurrencyInterface
import com.spotspal.fanartec.network.NetworkUtils.FIXER_KEY
import com.task.eurcurrencyconverter.R
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesData
import com.task.eurcurrencyconverter.utils.sharedPreferencesUtils.getSavedObjectFromPreference
import com.task.eurcurrencyconverter.utils.sharedPreferencesUtils.saveObjectToSharedPreference
import network.ApiExceptions
import network.NoInternetException
import network.SafeApiRequest
import sa.waqood.alborg_customer.utils.Constants.RATES_DATA_KEY


class CurrencyRepository : SafeApiRequest() {

    val apiService = ApiClient().client.create(CurrencyInterface::class.java)

    private val responseError = MutableLiveData<String>()
    fun getErrorResponseData(): MutableLiveData<String> {
        return responseError
    }

    private var currencyCurrencyRatesData = MutableLiveData<CurrencyRatesData> ()

    fun getCurrencyRatesData(): MutableLiveData<CurrencyRatesData> {
        return currencyCurrencyRatesData
    }

    fun getCurrencyRates(online : Boolean) {

        if(online) {
            Coroutines.main {
                try {
                    var response =
                        apiRequest { apiService.getCurrencyRates(FIXER_KEY) }

                    if (response.success) {
                        currencyCurrencyRatesData.postValue(response.rates)
                        saveObjectToSharedPreference(RATES_DATA_KEY, response.rates)
                    } else
                        responseError.postValue(response.error.info)
                } catch (e: ApiExceptions) {
                    responseError.postValue(MyApplication.getInstance()!!.resources.getString(R.string.there_error))
                } catch (e: NoInternetException) {
                    responseError.postValue(MyApplication.getInstance()!!.resources.getString(R.string.no_internet))

                } catch (e: Exception) {
                    Log.e("Error->", e.localizedMessage)
                }
            }
        }
        else
        {
            if(getSavedObjectFromPreference(RATES_DATA_KEY, CurrencyRatesData::class.java) != null)
                currencyCurrencyRatesData.postValue(getSavedObjectFromPreference(RATES_DATA_KEY, CurrencyRatesData::class.java))
            else
                currencyCurrencyRatesData.postValue(CurrencyRatesData())
        }

    }

}