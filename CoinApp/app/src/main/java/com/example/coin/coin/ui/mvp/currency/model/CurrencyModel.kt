package com.example.coin.coin.ui.mvp.currency.model

import com.example.coin.coin.data.network.CurrencyApi
import com.example.coin.coin.dto.CurrencyDataInfo
import com.example.coin.coin.dto.CurrencyDataLatest
import com.example.coin.coin.ui.mvp.currency.contract.CurrencyContract
import io.reactivex.Single
import javax.inject.Inject

class CurrencyModel @Inject constructor() : CurrencyContract.currencyModel {


    @Inject
    lateinit var currencyApi: CurrencyApi



    override fun getDataLatest(): Single<CurrencyDataLatest> {
        return currencyApi.getData()
    }

    override fun getDataInfo(currencyDataLatest: CurrencyDataLatest): Single<CurrencyDataInfo> {

        val listId = arrayListOf<String>()

        for (currency in currencyDataLatest.data) {
            listId.add(currency.id.toString())

        }

        return currencyApi.getDataInfo(listId.joinToString(","))
    }


}