package kktyu.xyz.zipcode_search_mvvm.datadetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kktyu.xyz.zipcode_search_mvvm.R

class DataDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.contentFrame, DataDetailFragment())
            commit()
        }
    }
}