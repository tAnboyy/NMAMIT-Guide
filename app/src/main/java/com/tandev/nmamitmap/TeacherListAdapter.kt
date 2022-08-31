package com.tandev.nmamitmap

import android.app.Activity
import android.content.ContentResolver.EXTRA_SIZE
import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.tandev.nmamitmap.R
import com.tandev.nmamitmap.teacher.Teacher

class TeacherListAdapter(private val context: Activity, private val arrayList: ArrayList<Teacher>): ArrayAdapter<Teacher>(context,
    R.layout.teacher_list_item, arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.teacher_list_item, null)

        val imageView: ImageView = view.findViewById(R.id.dp)
        val name: TextView = view.findViewById(R.id.tvName)
        val block: TextView = view.findViewById(R.id.tvBlock)
        val floor: TextView = view.findViewById(R.id.tvFloor)
        val branch: TextView = view.findViewById(R.id.tvBranch)
//        val phone: TextView = view.findViewById(R.id.tvPhone)

//        val imgFileName: String = branch.text.toString().lowercase() + "_" + phone.text.toString()

//        val imgFileName = "R.drawable.is_8431439127"
//        fun AppCompatActivity.getString(name: String): String {
//            return resources.getString(resources.getIdentifier(name, "string", packageName))
//        }
//        val resource = getString(android.content.ContentResolver.EXTRA_SIZE, imgFileName);

//        val url = "https://drive.google.com/open?id=1mKncEpNbc3K0ObXRc9sqjYpxHwbi8faZ"

        Glide.with(getContext())
            .load(arrayList[position].imgUrl)
//            .skipMemoryCache(true)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.placeholderdp)
            .into(imageView)

//        imageView.setImageResource(R.drawable.is_8431439127)

        name.text = arrayList[position].name
        branch.text = arrayList[position].branch
//        phone.text = arrayList[position].phone
        block.text = "Block: " + arrayList[position].block
        floor.text = "Floor: " + arrayList[position].floor

        return view
    }
}