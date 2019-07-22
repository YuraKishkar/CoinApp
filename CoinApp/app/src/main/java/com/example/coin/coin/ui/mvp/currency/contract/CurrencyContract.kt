package com.example.coin.coin.ui.mvp.currency.contract


import com.arellomobile.mvp.MvpView
import com.example.coin.coin.dto.CurrencyDataInfo
import com.example.coin.coin.dto.CurrencyDataLatest
import com.example.coin.coin.ui.fragments.currency.CurrencyDataUI
import io.reactivex.Single

interface CurrencyView : MvpView {
    fun onShowData(currencyDataUI: List<CurrencyDataUI>)
    fun onShowError(error: Throwable)
    fun onShowProgress(b: Boolean)
}

interface CurrencyContract {
    interface currencyPresenter {

        //        fun setView(CurrencyView: CurrencyView)
        fun requestData()
    }

    interface currencyModel {
        fun getDataLatest(): Single<CurrencyDataLatest>
//        fun getDataInfo(id: String): Single<CurrencyDataInfo>
        fun getDataInfo(currencyDataLatest: CurrencyDataLatest) : Single<CurrencyDataInfo>
    }
}