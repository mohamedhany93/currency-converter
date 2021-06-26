package com.task.eurcurrencyconverter.ui.currency_exchange

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.spotspal.fanartec.core.BaseViewModel
import com.spotspal.fanartec.core.MyApplication
import com.spotspal.fanartec.network.Coroutines
import com.task.eurcurrencyconverter.R
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRate
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesData
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRatesResponse
import com.task.eurcurrencyconverter.repository.CurrencyRepository
import kotlinx.android.synthetic.main.fragment_currency_exchange.*
import network.ApiExceptions
import network.NoInternetException
import sa.waqood.alborg_customer.utils.Constants

class ExchangeViewModel : BaseViewModel() {

    private var exchangeResult : MutableLiveData<Double> = MutableLiveData()
    fun getExchangeResult(): MutableLiveData<Double> {
        return exchangeResult
    }

    fun calculateExchangeResult(baseAmount:Double , exchangeRate:Double){
        exchangeResult.postValue(baseAmount*exchangeRate)
    }

}