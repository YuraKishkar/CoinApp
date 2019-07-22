package com.example.coin.coin.di.component

import com.example.coin.coin.App
import com.example.coin.coin.di.module.AppModule
import com.example.coin.coin.di.module.CurrencyModule
import com.example.coin.coin.di.module.RoomModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun currencyComponent(): CurrencyComponent

    class Initializer private constructor() {

        companion object {
            fun init(app: App): AppComponent {
                return DaggerAppComponent.builder()
                        .appModule(AppModule(app))
                        .build()

            }
        }
    }
}