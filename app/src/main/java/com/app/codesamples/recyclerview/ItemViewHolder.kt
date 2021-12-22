package com.app.codesamples.recyclerview

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.codesamples.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val text: TextView by lazy { itemView.findViewById(R.id.text_tv) }
    private val image: ImageView by lazy { itemView.findViewById(R.id.image_iv) }
    private val description: TextView by lazy { itemView.findViewById(R.id.description_tv) }
    private val date: TextView by lazy { itemView.findViewById(R.id.date_tv) }

    fun setData(item: ViewItem, context: Context, recyclerEventListener: RecyclerEventListener) {
        text.text = item.text
        description.text = item.description
        date.text = item.date
        Glide.with(context).load(item.imageUrl).into(image);
        image.setOnClickListener {
            recyclerEventListener.showSnackBar( "Image Clicked", Snackbar.LENGTH_SHORT)
        }
    }

}