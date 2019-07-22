package com.example.coin.coin.data.network

import com.example.coin.coin.dto.CurrencyDataInfo
import com.example.coin.coin.dto.CurrencyDataLatest
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface CurrencyApi {

    @Headers("X-CMC_PRO_API_KEY: a46d81af-bd34-45e8-9f4c-a318e3bf22a9")
    @GET("/v1/cryptocurrency/listings/latest")
    fun getData(): Single<CurrencyDataLatest>

    @Headers("X-CMC_PRO_API_KEY: a46d81af-bd34-45e8-9f4c-a318e3bf22a9")
    @GET("/v1/cryptocurrency/info")
    fun getDataInfo(@Query("id") id: String): Single<CurrencyDataInfo>
}