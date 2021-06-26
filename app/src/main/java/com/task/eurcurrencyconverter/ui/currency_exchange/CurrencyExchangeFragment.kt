package com.task.eurcurrencyconverter.ui.currency_exchange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.task.eurcurrencyconverter.R
import kotlinx.android.synthetic.main.fragment_currency_exchange.*
import sa.waqood.alborg_customer.utils.Constants.SELECTED_CURRENCY_NAME_KEY
import sa.waqood.alborg_customer.utils.Constants.SELECTED_CURRENCY_RATE_KEY

class CurrencyExchangeFragment : Fragment() {

    private val exchangeViewModel: ExchangeViewModel by lazy {
        ViewModelProvider(this).get(ExchangeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        //Observe on result amount change
        exchangeViewModel.getExchangeResult().observe(requireActivity(), Observer {
            etSelectedCurrencyAmount.setText(it.toString())
        })
    }

    private fun initView()
    {
        tvSelectedCurrency.text = requireArguments().getString(SELECTED_CURRENCY_NAME_KEY)
        exchangeViewModel.calculateExchangeResult(etBaseCurrencyAmount.text.toString().toDouble(),requireArguments().getDouble(SELECTED_CURRENCY_RATE_KEY))

        etBaseCurrencyAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if(!s.toString().isEmpty())
                    exchangeViewModel.calculateExchangeResult(s.toString().toDouble(),requireArguments().getDouble(SELECTED_CURRENCY_RATE_KEY))
            }
            override fun beforeTextChanged(s: CharSequence, start: Int,count: Int,after: Int ) { }
            override fun onTextChanged(s: CharSequence, start: Int,count: Int,after: Int ) { }
        })
    }
}