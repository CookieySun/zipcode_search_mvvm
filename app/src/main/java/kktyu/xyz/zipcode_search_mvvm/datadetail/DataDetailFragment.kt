package kktyu.xyz.zipcode_search_mvvm.datadetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kktyu.xyz.zipcode_search_mvvm.R
import kktyu.xyz.zipcode_search_mvvm.data.Address
import kktyu.xyz.zipcode_search_mvvm.databinding.FragmentDataDetailBinding


class DataDetailFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDataDetailBinding>(inflater,R.layout.fragment_data_detail,container,false)

        binding.address = Address()

        return binding.root
    }

}
