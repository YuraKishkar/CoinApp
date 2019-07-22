package com.example.coin.coin.dto

data class CurrencyDataInfo(
    val data: Map<String, Details>,
    val status: StatusInfo
//    val dataInfo: DataInfo,
//    val status: StatusInfo
)

//data class DataInfo(
//    val BTC: BTCInfo
//)


data class Details(
    val category: String,
    val id: Int,
    val logo: String,
    val name: String,
    val platform: Any,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    val urls: Urls
)

data class Urls(
    val announcement: List<Any>,
    val chat: List<Any>,
    val explorer: List<String>,
    val message_board: List<String>,
    val reddit: List<String>,
    val source_code: List<String>,
    val twitter: List<String>,
    val website: List<String>
)


data class StatusInfo(
    val credit_count: Int,
    val elapsed: Int,
    val error_code: Int,
    val error_message: String,
    val timestamp: String
)


