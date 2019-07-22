package com.example.coin.coin.data.database

import android.arch.persistence.room.*
import com.example.coin.coin.ui.fragments.currency.CurrencyDataUI
import io.reactivex.Single

@Dao
interface CurrencyDataDao {

    @Query("SELECT * FROM currency_table")
    fun getAll(): List<CurrencyDataUI>

    @Query("SELECT * FROM currency_table WHERE id = :id")
    fun getById(id: Long): Single<CurrencyDataUI>

    @Insert
    fun insert(currencyDataUI: List<CurrencyDataUI>)

    @Update
    fun update(currencyDataUI: List<CurrencyDataUI>)

    @Delete
    fun delete(currencyDataUI: List<CurrencyDataUI>)

}