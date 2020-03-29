package kktyu.xyz.zipcode_search_mvvm

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class GetApiData(url: String) {
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    fun getAddressInfo(parameter: String): Response<RecestResponse> {
        val service = retrofit.create(AddressApiInterface::class.java)
        return service.getAddress(parameter).execute()
    }
}