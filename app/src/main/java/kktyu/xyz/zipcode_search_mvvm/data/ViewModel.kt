package kktyu.xyz.zipcode_search_mvvm.data

import androidx.databinding.BaseObservable

class ViewModel : BaseObservable() {
    var address: String = ""
        set(value) {
            field = value
            notifyChange()
        }
}