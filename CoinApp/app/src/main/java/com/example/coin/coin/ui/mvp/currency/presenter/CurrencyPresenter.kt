package com.example.coin.coin.ui.mvp.currency.presenter

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.coin.coin.App
import com.example.coin.coin.data.database.AppDataBase
import com.example.coin.coin.data.database.CurrencyDataDao
import com.example.coin.coin.dto.CurrencyDataInfo
import com.example.coin.coin.dto.CurrencyDataLatest
import com.example.coin.coin.ui.mvp.currency.contract.CurrencyContract
import com.example.coin.coin.ui.mvp.currency.contract.CurrencyView
import com.example.coin.coin.ui.mvp.currency.model.CurrencyModel
import com.example.coin.coin.ui.mvp.currency.model.PreCurrencyDataUI
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class CurrencyPresenter : CurrencyContract.currencyPresenter,
    MvpPresenter<CurrencyView>() {

    init {
        App.componentApp().currencyComponent().inject(this)
    }


    @Inject
    lateinit var mCurrencyModel: CurrencyModel
    @Inject
    lateinit var mCurrencyDataDao: CurrencyDataDao

    @SuppressLint("CheckResult")
    override fun requestData() {
        viewState.onShowProgress(true)

        val currencyLatest: Single<CurrencyDataLatest> = mCurrencyModel.getDataLatest()
        val currencyInfo: Single<CurrencyDataInfo> = currencyLatest.flatMap { it -> mCurrencyModel.getDataInfo(it) }

        Single.zip(currencyLatest, currencyInfo, PreCurrencyDataUI())
            .map { list ->
                mCurrencyDataDao.delete(list)
                mCurrencyDataDao.insert(list)
                mCurrencyDataDao.getAll()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> viewState.onShowData(result) }
                , { error -> viewState.onShowError(error)})

    }

}
