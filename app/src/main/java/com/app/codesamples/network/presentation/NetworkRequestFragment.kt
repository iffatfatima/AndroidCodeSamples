package com.app.codesamples.network.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.codesamples.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetworkRequestFragment : Fragment() {

    companion object {
        val TAG: String = NetworkRequestFragment::class.java.simpleName
        fun newInstance() = NetworkRequestFragment()
    }

    private val viewModel: NetworkRequestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.network_request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(NetworkRequestViewModel::class.java)
        viewModel.dataLoading.observe(viewLifecycleOwner, {
            Log.d(TAG, it.state.toString() + it.message)
            //show progress here

        })
        viewModel.items.observe(viewLifecycleOwner, {
                Log.d(TAG, it.first + it.last)
            //update UI

        })
    }

}