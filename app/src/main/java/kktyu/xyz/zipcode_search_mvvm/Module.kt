package kktyu.xyz.zipcode_search_mvvm

import android.content.Context
import dagger.Module
import dagger.Provides
import kktyu.xyz.zipcode_search_mvvm.data.ViewModel

@Module
class Module {
    @Provides
    fun getViewModel(): ViewModel {
        return ViewModel()
    }

    @Provides
    fun getApi(context: Context): String {
        return context.getString(R.string.base_url)
    }

    @Provides
    fun getGetApiDataInstance(baseUrl: String): GetApiData {
        return GetApiData(baseUrl)
    }
}