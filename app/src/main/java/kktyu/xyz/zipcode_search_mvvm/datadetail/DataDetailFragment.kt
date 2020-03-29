package kktyu.xyz.zipcode_search_mvvm.datadetail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kktyu.xyz.zipcode_search_mvvm.DaggerMainComponent
import kktyu.xyz.zipcode_search_mvvm.GetApiData
import kktyu.xyz.zipcode_search_mvvm.R
import kktyu.xyz.zipcode_search_mvvm.data.ViewModel
import kktyu.xyz.zipcode_search_mvvm.databinding.FragmentDataDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataDetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: ViewModel
    @Inject
    lateinit var baseUrl: String
    @Inject
    lateinit var getApiData: GetApiData

    private lateinit var binding: FragmentDataDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainComponent.builder().application(activity!!.applicationContext).build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstHalfNumber.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (binding.firstHalfNumber.text.length == 3) binding.secondHalfNumber.requestFocus()
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
    }

    private fun fillText() {
        if (binding.firstHalfNumber.text.length == 3 && binding.secondHalfNumber.text.length == 4) {
            val zipCode =
                binding.firstHalfNumber.text.toString() + binding.secondHalfNumber.text.toString()
            val response = getAddress(zipCode)

            val address: String =
                if (response.isSuccessful) {
                    var resultText = ""
                    for (addresses in response.body()!!.results) {
                        resultText += (
                                addresses.address1
                                        + addresses.address2
                                        + addresses.address3
                                        + "%n"
                                )
                    }
                    resultText.format()
                } else {
                    Log.e("getAddress", "Json:$response/zipCode:$zipCode")
                    getString(R.string.error)
                }
            binding.viewModel?.address = address
        }
    }

    private fun getAddress(zipCode: String) = runBlocking {
        withContext(Dispatchers.IO) {
            return@withContext getApiData.getAddressInfo(zipCode)
        }
    }
}