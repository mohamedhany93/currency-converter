package com.task.eurcurrencyconverter.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.spotspal.fanartec.core.BaseViewModel
import com.spotspal.fanartec.core.MyApplication
import com.spotspal.fanartec.network.Coroutines
import com.task.eurcurrencyconverter.R
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRate
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesResponse
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesData
import com.task.eurcurrencyconverter.repository.CurrencyRepository
import network.ApiExceptions
import network.NoInternetException

class CurrencyViewModel : BaseViewModel() {

    var currencyRepository = CurrencyRepository()

    private var currencyCurrencyRatesData: MutableLiveData<ArrayList<CurrencyRate>>? = null

    fun getCurrencyRatesData(): MutableLiveData<ArrayList<CurrencyRate>>? {
        if (currencyCurrencyRatesData == null) {
            currencyCurrencyRatesData = MutableLiveData()
        }
        return currencyCurrencyRatesData
    }

    fun getCurrencyRates()
    {
        Coroutines.main {
            try {
                var response =
                    currencyRepository.getCurrencyRates()

                if (response.success ) {
                    if(response.currencies==null)
                        response.currencies = ArrayList<CurrencyRate>()
                    else
                        response.currencies.clear()

                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.EGP),response.rates.EGP))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.USD),response.rates.USD))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.AED),response.rates.AED))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.AFN),response.rates.AFN))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.ALL),response.rates.ALL))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.AMD),response.rates.AMD))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.ANG),response.rates.ANG))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.AOA),response.rates.AOA))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.ARS),response.rates.ARS))
                    response.currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.AUD),response.rates.AUD))

                    currencyCurrencyRatesData!!.postValue(response.currencies)
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
}