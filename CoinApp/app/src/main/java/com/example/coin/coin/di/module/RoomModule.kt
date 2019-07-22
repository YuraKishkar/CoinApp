package com.example.coin.coin.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.coin.coin.data.database.AppDataBase
import com.example.coin.coin.data.database.CurrencyDataDao
import com.example.coin.coin.di.scope.CurrencyFragmentScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {


    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "currency_table"
        ).build()
    }


    @Provides
    @Singleton
    fun provideCurrencyDao(appDataBase: AppDataBase): CurrencyDataDao {
        return appDataBase.currencyDataDao()
    }

}