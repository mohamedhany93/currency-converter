package com.task.eurcurrencyconverter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.task.eurcurrencyconverter.R
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRate
import com.task.eurcurrencyconverter.ui.Adapter.CurrenciesAdapter
import com.task.eurcurrencyconverter.utils.LoadingDialog
import kotlinx.android.synthetic.main.fragment_currensy_rates.*

class CurrencyRatesFragment : Fragment() {


    private val currencyViewModel: CurrencyViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CurrencyViewModel::class.java)
    }
    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog(requireActivity())
    }

    lateinit var currenciesAdapter: CurrenciesAdapter
    var currencyRatesList = ArrayList<CurrencyRate>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currensy_rates, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
    }

    private fun initView()
    {
        initCurrenciesRv()
    }

    private fun initData()
    {

        loadingDialog.show()
        //Load currency rates data
        currencyViewModel.getCurrencyRates()

        currencyViewModel.getCurrencyRatesData()?.observe(requireActivity(), Observer {
            loadingDialog.cancel()
            //view currency rates
            currencyRatesList.clear()
            currencyRatesList.addAll(it)
            currenciesAdapter.notifyDataSetChanged()
        })

        currencyViewModel.getApiResponseError().observe(requireActivity(), Observer {
            loadingDialog.cancel()
            Toast.makeText(activity , it , Toast.LENGTH_LONG).show()
        })
    }

    private fun initCurrenciesRv() {
        currenciesAdapter =
            CurrenciesAdapter(currencyRatesList,
                object :
                    CurrenciesAdapter.ClickListener {
                    override fun onItemClick(pos: Int) {
                    }
                }
            )
        rvCurrencies.adapter=currenciesAdapter
    }

}