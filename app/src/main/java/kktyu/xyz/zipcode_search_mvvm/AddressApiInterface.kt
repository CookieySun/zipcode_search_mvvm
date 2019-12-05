package kktyu.xyz.zipcode_search_mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApiInterface {
    @GET("http://zipcloud.ibsnet.co.jp/api/search")
    fun getAddress(@Query("zipcode") zipcode: String): Call<RecestResponse>
}