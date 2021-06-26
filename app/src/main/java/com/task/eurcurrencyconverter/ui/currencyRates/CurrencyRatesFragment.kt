package com.task.eurcurrencyconverter.ui.currencyRates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.task.eurcurrencyconverter.R
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRate
import com.task.eurcurrencyconverter.ui.currencyRates.Adapter.CurrenciesAdapter
import com.task.eurcurrencyconverter.ui.currency_exchange.CurrencyExchangeFragment
import com.task.eurcurrencyconverter.utils.LoadingDialog
import kotlinx.android.synthetic.main.fragment_currensy_rates.*
import sa.waqood.alborg_customer.utils.Constants.SELECTED_CURRENCY_NAME_KEY
import sa.waqood.alborg_customer.utils.Constants.SELECTED_CURRENCY_RATE_KEY


class CurrencyRatesFragment : Fragment() {


    private val currencyViewModel: CurrencyViewModel by lazy {
        ViewModelProvider(this).get(CurrencyViewModel::class.java)
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
        switchOnline.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                loadingDialog.show()
                //Load currency rates data
                currencyViewModel.getCurrencyRates(isChecked)
            }
        })
        btnRefresh.setOnClickListener {
            loadingDialog.show()
            //Load currency rates data
            currencyViewModel.getCurrencyRates(switchOnline.isChecked)
        }
    }

    private fun initData()
    {

        loadingDialog.show()
        //Load currency rates data
        currencyViewModel.getCurrencyRates(switchOnline.isChecked)

        currencyViewModel.getCurrencyRatesData().observe(requireActivity(), Observer {
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
                        val bundle = Bundle()
                        bundle.putString(SELECTED_CURRENCY_NAME_KEY,currencyRatesList[pos].currencyName)
                        bundle.putDouble(SELECTED_CURRENCY_RATE_KEY,currencyRatesList[pos].rate)
                        openCurrencyExchangeFragment(bundle)
                    }
                }
            )
        rvCurrencies.adapter=currenciesAdapter
    }

    private fun openCurrencyExchangeFragment(bundle :Bundle) {
        val currencyExchangeFragment: Fragment = CurrencyExchangeFragment()
        currencyExchangeFragment.arguments = bundle
        val transaction: FragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction()
        transaction.add(R.id.nav_host_fragment, currencyExchangeFragment)
        transaction.addToBackStack("Home")
        transaction.commit()
    }

}