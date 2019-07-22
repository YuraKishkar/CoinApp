package com.example.coin.coin.di.module

import com.example.coin.coin.data.network.CurrencyApi
import com.example.coin.coin.di.scope.CurrencyFragmentScope
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class CurrencyModule {


    @Provides
    @CurrencyFragmentScope
    fun privideApiCurrency(): CurrencyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }


}