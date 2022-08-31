package com.tandev.nmamitmap

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tandev.nmamitmap.Place
import com.tandev.nmamitmap.R
import com.tandev.nmamitmap.teacher.Teacher

class PlaceListAdapter(private val context: Activity, private val arrayList: ArrayList<Place>): ArrayAdapter<Place>(context,
    R.layout.place_list_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.place_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.dp)
        val name: TextView = view.findViewById(R.id.tvName)
        val altNames: TextView = view.findViewById(R.id.tvAltNames)

        Glide.with(getContext())
            .load(arrayList[position].imgUrl)
//            .skipMemoryCache(true)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.placeholder_place)
            .into(imageView)

//        imageView.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        altNames.text = arrayList[position].note

        return view
    }
}