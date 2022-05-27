package com.example.nmamitmap

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nmamitmap.databinding.ItemViewPagerBinding
import com.google.android.gms.maps.model.LatLng


class ViewPagerAdapter(
    private val context: Context,
    val images: List<Int>,
//    var tabNo: Int
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
    private lateinit var binding: ItemViewPagerBinding

    //    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ViewPagerViewHolder(var binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        binding = ItemViewPagerBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
//        Toast.makeText(context, "$tabNo", Toast.LENGTH_SHORT)

        val listView = holder.binding.listView;
        val names = arrayOf("LC block", "NC block");

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            context, android.R.layout.simple_list_item_1, names
        )

        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
            val intent = Intent(context, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
            var lat = 13.188421854033647
            var lng = 74.9378861798709
            intent.putExtra("key-lat", lat);
            intent.putExtra("key-lng", lng);
            intent.putExtra("disCur", 1);

            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return images.size
    }
}