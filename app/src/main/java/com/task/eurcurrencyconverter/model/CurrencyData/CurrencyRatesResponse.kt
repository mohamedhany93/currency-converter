package com.task.eurcurrencyconverter.model.CurrencyData

class CurrencyRatesResponse (val success: Boolean,
                             val error: ResponseError,
                             val timestamp: Long,
                             val base: String,
                             val date: String,
                             val rates:CurrencyRatesData){

}