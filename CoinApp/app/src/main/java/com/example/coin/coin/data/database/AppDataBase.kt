package com.example.coin.coin.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.coin.coin.ui.fragments.currency.CurrencyDataUI

@Database(entities = [CurrencyDataUI::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun currencyDataDao(): CurrencyDataDao

}