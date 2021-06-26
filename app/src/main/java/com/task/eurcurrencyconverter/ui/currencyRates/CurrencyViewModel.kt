package com.task.eurcurrencyconverter.ui.currencyRates

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

class CurrencyViewModel : BaseViewModel() {

    var currencyRepository = CurrencyRepository()
    init {
        responseError = currencyRepository.getErrorResponseData()
    }
    
    var currencies : ArrayList<CurrencyRate> = ArrayList<CurrencyRate>()

    private var currencyCurrencyRatesData: MutableLiveData<ArrayList<CurrencyRate>>? = null

    fun getCurrencyRatesData(): MutableLiveData<ArrayList<CurrencyRate>> {
        if (currencyCurrencyRatesData == null) {
            currencyCurrencyRatesData = MutableLiveData()
        }
        return currencyCurrencyRatesData!!
    }

    fun getCurrencyRates(online:Boolean)
    {
        currencyRepository.getCurrencyRates(online)
        currencyRepository.getCurrencyRatesData().observeForever(mObserver)
    }

    val mObserver: Observer<CurrencyRatesData> = Observer { it ->

        currencies.clear()

        currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(
                    R.string.EGP
                ), it.EGP))
        currencies.add(CurrencyRate(MyApplication.appInstance.resources.getString(R.string.USD), it.USD))
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.AED
                ), it.AED
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.AFN
                ), it.AFN
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.ALL
                ), it.ALL
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.AMD
                ), it.AMD
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.ANG
                ), it.ANG
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.AOA
                ), it.AOA
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.ARS
                ), it.ARS
            )
        )
        currencies.add(
            CurrencyRate(
                MyApplication.appInstance.resources.getString(
                    R.string.AUD
                ), it.AUD
            )
        )

        Log.d("gdfgh",currencies.size.toString())
        currencyCurrencyRatesData!!.postValue(currencies)
    }

    override fun onCleared() {
        super.onCleared()
        currencyRepository.getCurrencyRatesData().removeObserver(mObserver)
    }
}