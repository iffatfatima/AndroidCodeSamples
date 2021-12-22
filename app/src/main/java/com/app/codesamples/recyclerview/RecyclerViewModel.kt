package com.app.codesamples.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.*

class RecyclerViewModel: ViewModel() {

    val data: LiveData<ArrayList<ViewItem>>
        get() = _data
    private val _data = MutableLiveData<ArrayList<ViewItem>>(arrayListOf())

    init {
        loadData()
    }

    private fun loadData() {
        // This is a coroutine scope with the lifecycle of the ViewModel
        viewModelScope.launch {
            val item = Item("Firstname",
                "Lastname",
                "https://picsum.photos/id/237/300/150",
                "Hi! I am an adorable doggo.",
                Date(System.currentTimeMillis())
            )
            val list = arrayListOf(item.toViewItem(), item.toViewItem(), item.toViewItem(), item.toViewItem(),
                item.toViewItem(), item.toViewItem(), item.toViewItem(), item.toViewItem())
            _data.postValue(list)
        }
    }

    fun Item.toViewItem() = ViewItem(
        text = "$first $last",
        imageUrl= imageUrl,
        description = longtext,
        date = date.toString()
    )
}




