package com.example.coin.coin.di.component

import com.example.coin.coin.di.module.CurrencyModule
import com.example.coin.coin.di.module.RoomModule
import com.example.coin.coin.di.scope.CurrencyFragmentScope
import com.example.coin.coin.ui.mvp.currency.presenter.CurrencyPresenter
import dagger.Subcomponent

@CurrencyFragmentScope
@Subcomponent(modules = [CurrencyModule::class])
interface CurrencyComponent {
    fun inject(currencyPresenter: CurrencyPresenter)
}