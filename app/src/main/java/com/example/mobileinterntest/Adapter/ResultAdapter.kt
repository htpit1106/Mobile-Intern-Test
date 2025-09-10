package com.example.mobileinterntest.Adapter

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileinterntest.R
import com.example.mobileinterntest.data.model.Place

class ResultAdapter : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private var currentQuery: String  = "";

    // List place
    private var places: List<Place> = emptyList()

    // update data
    fun updateData(places: List<Place>, query: String) {
        this.places = places
        this.currentQuery = query
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

        holder.txtNameLocation.text = holder.highlightQuery(place.displayName, currentQuery)


    }

    override fun getItemCount(): Int = places.size
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val txtNameLocation = itemView.findViewById<TextView>(R.id.txtNameLocation)
         val imgRedirect = itemView.findViewById<ImageView>(R.id.imgRedirect)
         fun bind(place: Place) {
            txtNameLocation.text = place.displayName
        }
        fun highlightQuery(fullText: String, query: String) : CharSequence{
           if(query.isEmpty()){
               return fullText
           }
            val spannable = SpannableString(fullText)
            val startIndex = fullText.lowercase().indexOf(query.lowercase())
            if(startIndex >= 0){
                val endIndex = startIndex + query.length
                spannable.setSpan(

                    StyleSpan(Typeface.BOLD),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannable.setSpan(
                    ForegroundColorSpan(Color.BLUE),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

            }
            return spannable

        }

    }

}