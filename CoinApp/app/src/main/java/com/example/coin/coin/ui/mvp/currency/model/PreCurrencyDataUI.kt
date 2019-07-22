package com.example.coin.coin.ui.mvp.currency.model

import com.example.coin.coin.dto.CurrencyDataInfo
import com.example.coin.coin.dto.CurrencyDataLatest
import com.example.coin.coin.ui.fragments.currency.CurrencyDataUI
import io.reactivex.functions.BiFunction

open class PreCurrencyDataUI : BiFunction<CurrencyDataLatest, CurrencyDataInfo, List<CurrencyDataUI>> {

    val currencyDataUI = mutableListOf<CurrencyDataUI>()

    override fun apply(currencyLatest: CurrencyDataLatest, currencyInfo: CurrencyDataInfo): List<CurrencyDataUI> {
        for (i in 0..(currencyLatest.data.count()-1)) {
            currencyInfo.data[currencyLatest.data[i].id.toString()]?.logo?.let {
               currencyDataUI.add(CurrencyDataUI(
                    id = currencyLatest.data[i].id,
                    name = currencyLatest.data[i].name,
                    symbol = currencyLatest.data[i].symbol,
                    percent_change_24h = currencyLatest.data[i].quote.USD.percent_change_24h,
                    price = currencyLatest.data[i].quote.USD.price,
                    volume_24h = currencyLatest.data[i].quote.USD.volume_24h,
                    url = it
                ))
            }
        }
        return  currencyDataUI
    }


}