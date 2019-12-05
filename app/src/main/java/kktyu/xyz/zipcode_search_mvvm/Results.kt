package kktyu.xyz.zipcode_search_mvvm

data class Results(
    val address1: String,
    val address2: String,
    val address3: String,
    val kana1: String,
    val kana2: String,
    val kana3: String,
    val prefcode: String,
    val zipcode: String
)