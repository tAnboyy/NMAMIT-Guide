package com.example.nmamitmap

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.nmamitmap.teacher.Teacher

class TeacherListAdapter(private val context: Activity, private val arrayList: ArrayList<Teacher>): ArrayAdapter<Teacher>(context, R.layout.teacher_list_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.teacher_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.dp)
        val name: TextView = view.findViewById(R.id.tvName)
        val branch: TextView = view.findViewById(R.id.tvBranch)
        val phone: TextView = view.findViewById(R.id.tvPhone)

//        imageView.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        branch.text = arrayList[position].branch
        phone.text = "+91-" + arrayList[position].phone

        return view
    }
}