package com.example.mobileinterntest.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileinterntest.R
import com.example.mobileinterntest.data.model.Place

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {


    // List place
    private var places: List<Place> = emptyList()
    fun setPlaces(places: List<Place>) {
        this.places = places
        notifyDataSetChanged()
    }

    // update data
    fun updateData(places: List<Place>, query: String) {
        this.places = places
        notifyDataSetChanged()
    }

     interface setOnClickListener {
        fun onItemClick(place: Place)
        fun onRedirectClick(place: Place)
    }
    private var listener: setOnClickListener? = null
     fun setOnClickListener(listener: setOnClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val place = places[position]
        holder.bind(place)

        holder.itemView.setOnClickListener {
            listener?.onItemClick(place)
        }
        holder.imgRedirect.setOnClickListener {
            listener?.onRedirectClick(place)
        }


    }

    override fun getItemCount(): Int = places.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val txtNameLocation = itemView.findViewById<TextView>(R.id.txtNameLocation)
         val imgRedirect = itemView.findViewById<ImageView>(R.id.imgRedirect)
        fun bind(place: Place) {
            txtNameLocation.text = place.displayName
        }
    }

}