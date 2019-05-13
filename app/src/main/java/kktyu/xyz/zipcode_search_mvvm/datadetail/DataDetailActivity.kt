package kktyu.xyz.zipcode_search_mvvm.datadetail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kktyu.xyz.zipcode_search_mvvm.R
import kotlinx.android.synthetic.main.activity_data_detail.*

class DataDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.contentFrame, DataDetailFragment())
            commit()
        }

        firstHalfNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (firstHalfNumber.text.length == 3) secondHalfNumber.requestFocus()
                fillText()
            }
        })
        secondHalfNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                fillText()
            }
        })
    }

    private fun fillText() {
        if (firstHalfNumber.text.length == 3 && secondHalfNumber.text.length == 4) {
//            getAddress(firstHalfNumber.text.toString() + secondHalfNumber.text.toString())
        }
    }

    /*private fun getAddress(zipCode: String) = GlobalScope.launch(Dispatchers.Main) {
        val http = HttpUtil()
        withContext(Dispatchers.Default) { http.httpGET1(getString(R.string.URL) + zipCode) }.let {
            val result = Json.parse(it).asObject()
            if (result.get("status").asInt() == 200 && !result.get("results").isNull) {
                var resultText: String = ""
                for (addresses in result.get("results").asArray()) {
                    val address = addresses.asObject()
                    resultText += (address.get("address1").toString()
                            + address.get("address2").toString()
                            + address.get("address3").toString())
                            .replace("\"", "") + "%n"
                }
                resultView.text = resultText.format()
            } else {
                resultView.text = getString(R.string.error)
                Log.e("getAddress", "Json:$result/zipCode:$zipCode")
            }
        }
    }*/

//    private fun findOrCreateViewFragment() =
//            supportFragmentManager.findFragmentById(R.id.contentFrame) ?:
//            DataDetailFragment.newInstance(intent.getStringExtra(EXTRA_TASK_ID))
}