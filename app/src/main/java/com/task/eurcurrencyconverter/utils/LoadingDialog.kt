package com.task.eurcurrencyconverter.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.Log
import com.task.eurcurrencyconverter.R
import kotlinx.android.synthetic.main.loading_dialog.*

class LoadingDialog(context:Context) :Dialog(context)  {

    init {
        requestWindowFeature(1)
        setCancelable(false)
        setContentView(R.layout.loading_dialog)
        window!!.setBackgroundDrawable(ColorDrawable(0))
    }

}