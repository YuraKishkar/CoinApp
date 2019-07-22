package com.example.coin.coin

import android.app.Application
import com.example.coin.coin.di.component.AppComponent

class App : Application() {


    companion object {
        private lateinit var app: App
        private val componentApp: AppComponent by lazy {
            AppComponent.Initializer.init(app)
        }


        fun componentApp(): AppComponent {
            return componentApp
        }
    }


    override fun onCreate() {
        super.onCreate()
        app = this
    }


}