package com.task.eurcurrencyconverter.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.eurcurrencyconverter.R
import com.task.eurcurrencyconverter.model.CurrencyData.CurrencyRate
import kotlinx.android.synthetic.main.row_currency.view.*


public class CurrenciesAdapter (
    internal var itemList: ArrayList<CurrencyRate>,
    var clickListener: ClickListener
) :
    RecyclerView.Adapter<CurrenciesAdapter.MViewHolder>() {

    interface ClickListener {
        fun onItemClick(pos: Int)
    }

        override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.row_currency, viewGroup, false)

            return MViewHolder(view)
        }


        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onBindViewHolder(viewHolder: MViewHolder, pos: Int) {
            var item =itemList[pos]

            viewHolder.itemView.tvCurrencyName.text = item.currencyName
            viewHolder.itemView.tvCurrencyRate.text = item.rate.toString()

            viewHolder.itemView.setOnClickListener {
                clickListener.onItemClick(pos)
            }

        }
        inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    }