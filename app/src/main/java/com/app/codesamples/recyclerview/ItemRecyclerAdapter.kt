package com.app.codesamples.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.codesamples.R

class ItemRecyclerAdapter(private val ctx: Context,
                          private val recyclerEventListener: RecyclerEventListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: ArrayList<ViewItem>

    init {
        this.itemList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false);
        return  ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).setData(itemList[position], ctx, recyclerEventListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun replaceList(list: ArrayList<ViewItem>) {
        itemList.clear()
        itemList = list
        notifyDataSetChanged()
    }

    fun addToList(item: ViewItem) {
        itemList.add(item)
        notifyItemChanged(itemList.size-1)
    }
    fun removeFromList(position: Int) {
        if(itemList.size >= position) {
            itemList.removeAt(position)
            notifyItemChanged(position)
            notifyItemRangeChanged(position, itemList.size)
        }
    }


}