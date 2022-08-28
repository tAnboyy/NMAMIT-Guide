package com.example.nmamitmap

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.nmamitmap.teacher.Teacher

class PlaceListAdapter(private val context: Activity, private val arrayList: ArrayList<Place>): ArrayAdapter<Place>(context, R.layout.place_list_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.place_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.dp)
        val name: TextView = view.findViewById(R.id.tvName)
        val altNames: TextView = view.findViewById(R.id.tvAltNames)

//        imageView.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        altNames.text = arrayList[position].vicinity

        return view
    }
}