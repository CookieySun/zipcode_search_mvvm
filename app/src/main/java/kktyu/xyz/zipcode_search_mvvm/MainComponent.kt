package kktyu.xyz.zipcode_search_mvvm

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import kktyu.xyz.zipcode_search_mvvm.datadetail.DataDetailFragment

@Component(modules = [AndroidInjectionModule::class, Module::class])
interface MainComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(context: Context): Builder

        fun build(): MainComponent
    }

    fun inject(fragment: DataDetailFragment)
}