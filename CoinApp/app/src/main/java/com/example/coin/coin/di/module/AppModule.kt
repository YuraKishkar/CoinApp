package com.example.coin.coin.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.coin.coin.App
import com.example.coin.coin.data.database.AppDataBase
import com.example.coin.coin.data.database.CurrencyDataDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: App) {


    @Provides
    @Singleton
    fun provideApp(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

}