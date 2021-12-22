package com.app.codesamples.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.codesamples.R
import com.google.android.material.snackbar.Snackbar

class RecyclerViewFragment : Fragment(), LifecycleOwner, RecyclerEventListener {

    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: RecyclerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = context?.let { ItemRecyclerAdapter(it, this) }

        viewModel = ViewModelProvider(this).get(RecyclerViewModel::class.java)
        viewModel.data.observe(viewLifecycleOwner, itemListUpdateObserver)

    }

    private var itemListUpdateObserver: Observer<ArrayList<ViewItem>> = Observer<ArrayList<ViewItem>> {
        (recyclerView.adapter as ItemRecyclerAdapter).replaceList(it)
    }
    companion object {
        val TAG: String = RecyclerViewFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = RecyclerViewFragment()
    }

    override fun showSnackBar(content: String, length: Int) {
        view?.let { Snackbar.make(it, content, length).show() }
    }
}