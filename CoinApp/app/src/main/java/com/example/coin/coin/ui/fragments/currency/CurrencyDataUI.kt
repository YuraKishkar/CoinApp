package com.example.coin.coin.ui.fragments.currency

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency_table")
data class CurrencyDataUI(
    @PrimaryKey
    var id: Int = 0,

    var name: String = "Null",
    var symbol: String = "Null",
    var percent_change_24h: Double = 0.0,
    var price: Double = 0.0,
    var volume_24h: Double = 0.0,
    var url : String = "Null"
)

