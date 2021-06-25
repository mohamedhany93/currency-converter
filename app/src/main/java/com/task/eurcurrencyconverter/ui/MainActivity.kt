package com.task.eurcurrencyconverter.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.task.eurcurrencyconverter.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openCurrencyRatesFragment()
    }

    private fun openCurrencyRatesFragment() {

        val productDetailsFragment: Fragment = CurrencyRatesFragment()
        val transaction: FragmentTransaction = getSupportFragmentManager().beginTransaction()
        transaction.add(R.id.nav_host_fragment, productDetailsFragment)
        transaction.addToBackStack("Home")
        transaction.commit()
    }
}