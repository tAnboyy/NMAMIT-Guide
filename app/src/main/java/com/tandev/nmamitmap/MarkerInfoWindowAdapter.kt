package com.tandev.nmamitmap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tandev.nmamitmap.place.Place
import com.tandev.nmamitmap.place.PlacesReader
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {

    private val places: List<Place> by lazy {
        PlacesReader(context).read()
    }

    override fun getInfoContents(marker: Marker): View? {
        // 1. Get tag
        val place = marker.tag as? Place ?: return null

        // 2. Inflate view and set title, address and rating
        val view = LayoutInflater.from(context).inflate(R.layout.marker_info_contents, null)
        view.findViewById<TextView>(R.id.text_view_title).text = place.name
        view.findViewById<TextView>(R.id.text_view_address).text = place.note
        if (place.phone != 0L) view.findViewById<TextView>(R.id.tvPh).text = place.phone.toString() else view.findViewById<TextView>(
            R.id.tvPh
        ).visibility = View.INVISIBLE

        val iv: ImageView = view.findViewById<ImageView>(R.id.ivIW)

//        places.forEach { place ->
//            Glide.with(context)
//                .load(place.imgUrl)
//        }

        Glide.with(context)
            .load(place.imgUrl)
//            .skipMemoryCache(true)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.placeholder_place)
            .into(iv)

        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        // Return null to indicate that the default window (white bubble) should be used
        return null
    }
}