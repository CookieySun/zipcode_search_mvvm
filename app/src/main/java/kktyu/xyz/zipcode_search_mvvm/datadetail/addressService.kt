package kktyu.xyz.zipcode_search_mvvm.datadetail

import kktyu.xyz.zipcode_search_mvvm.data.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface addressService{
    @GET("/{zipcode}")
    fun getaddress(@Path("zipcode") zipcode:Int):Call<List<Address>>
}