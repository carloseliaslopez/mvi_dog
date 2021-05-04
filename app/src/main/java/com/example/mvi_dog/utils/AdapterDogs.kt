package com.example.mvi_dog.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvi_dog.R
import com.example.mvi_dog.model.Dog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_dog.view.*

class AdapterDogs () : RecyclerView.Adapter<AdapterDogs.ViewHolder>() {

    lateinit var items: ArrayList<Dog>

    override fun getItemCount(): Int {
        return if(::items.isInitialized){
            items.size
        }else{
            0
        }

    }
    fun setDogs(items: List<Dog>) {
        this.items = items as ArrayList<Dog>
        notifyDataSetChanged()
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false))

    // Binds each item in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model= items[position]
        holder.sizeInfo.text="Ancho: ${model.width}  Largo: ${model.height}"
        Picasso.get()
            .load(model.url)
            .into( holder.dogImage)
    }
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each picture to
        val dogImage: ImageView = view.dog_image
        val sizeInfo: TextView = view.size_info
    }
}
