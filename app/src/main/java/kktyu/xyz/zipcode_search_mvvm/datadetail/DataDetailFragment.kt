package kktyu.xyz.zipcode_search_mvvm.datadetail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.eclipsesource.json.Json
import kktyu.xyz.zipcode_search_mvvm.R
import kktyu.xyz.zipcode_search_mvvm.data.Jusho
import kktyu.xyz.zipcode_search_mvvm.databinding.FragmentDataDetailBinding
import kktyu.xyz.zipcode_search_mvvm.util.HttpUtil
import kotlinx.android.synthetic.main.fragment_data_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DataDetailFragment : Fragment() {

    private lateinit var binding: FragmentDataDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_data_detail,
            container,
            false
        )

        binding.firstHalfNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (firstHalfNumber.text.length == 3) secondHalfNumber.requestFocus()
                fillText()
            }
        })

        binding.secondHalfNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                fillText()
            }
        })

        return binding.root
    }

    private fun fillText() {
        if (firstHalfNumber.text.length == 3 && secondHalfNumber.text.length == 4) {
            getAddress(firstHalfNumber.text.toString() + secondHalfNumber.text.toString())
        }
    }

    private fun getAddress(zipCode: String) = GlobalScope.launch(Dispatchers.Main) {
        val http = HttpUtil()
        withContext(Dispatchers.Default) { http.httpGET1(getString(R.string.URL) + zipCode) }.let {
            val result = Json.parse(it).asObject()
            if (result.get("status").asInt() == 200 && !result.get("results").isNull) {
                var resultText = ""
                for (addresses in result.get("results").asArray()) {
                    val address = addresses.asObject()
                    resultText += (address.get("address1").toString()
                            + address.get("address2").toString()
                            + address.get("address3").toString())
                        .replace("\"", "") + "%n"
                }
                binding.jusho = Jusho(resultText.format())
            } else {
                resultView.text = getString(R.string.error)
                Log.e("getAddress", "Json:$result/zipCode:$zipCode")
            }
        }
    }


}
