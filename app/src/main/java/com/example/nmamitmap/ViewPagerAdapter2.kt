package com.example.nmamitmap

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nmamitmap.databinding.ItemViewPagerBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Collections.sort


class ViewPagerAdapter2(
    private val context: Context,
    val images: List<Int>,
//    var tabNo: Int
) : RecyclerView.Adapter<ViewPagerAdapter2.ViewPagerViewHolder>() {
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

        if (position == 0) {
//            food
            val listView = holder.binding.listView;
            val names = arrayOf("Durga canteen", "Cafe On The Corner");

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
                intent.putExtra("viaIntent", 1);

                context.startActivity(intent)
            }
        } else if (position == 1) {
//            blocks
            val listView = holder.binding.listView;
            val names = arrayOf("LC block", "NC block", "LH block", "Admin");

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                context, android.R.layout.simple_list_item_1, names
            )

            listView.adapter = arrayAdapter
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(context, MapsActivity::class.java)
                val lat: Double;
                val lng: Double;
                if (i == 0) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 1) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 2) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183575358800482
                    lng = 74.93880910912642
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                }
            }
        } else if (position == 2) {
            val listView = holder.binding.listView;
            val names = arrayOf("Usha Divakarla", "Sapna S", "Vasudeva Pai", "Pratheeksha Hegde", "Balaji N");

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                context, android.R.layout.simple_list_item_1, names
            )

            listView.adapter = arrayAdapter
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(context, MapsActivity::class.java)
                val lat: Double;
                val lng: Double;
                if (i == 0) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 1) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 2) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183575358800482
                    lng = 74.93880910912642
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                }
            }
        } else if (position == 3) {
            val listView = holder.binding.listView;
            val names = arrayOf("Sambhram", "Sadananda");

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                context, android.R.layout.simple_list_item_1, names
            )

            listView.adapter = arrayAdapter
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(context, MapsActivity::class.java)
                val lat: Double;
                val lng: Double;
                if (i == 0) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 1) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 2) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183575358800482
                    lng = 74.93880910912642
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                }
            }
        }

        else if (position == 4) {
//            ground
            val listView = holder.binding.listView;
            val names = arrayOf("TODO");

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                context, android.R.layout.simple_list_item_1, names
            )

            listView.adapter = arrayAdapter
            listView.setOnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(context, MapsActivity::class.java)
                val lat: Double;
                val lng: Double;
                if (i == 0) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 1) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183376567105734
                    lng = 74.9335084245757
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                } else if (i == 2) {
                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
//            var latlng = LatLng(16.099108, -22.812924);
                    lat = 13.183575358800482
                    lng = 74.93880910912642
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    context.startActivity(intent)
                }
            }
        }
//        else if (position == 6) {
//            val listView = holder.binding.listView;
//            val names = arrayOf("Sanmathi");
//
//            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                context, android.R.layout.simple_list_item_1, names
//            )
//
//            listView.adapter = arrayAdapter
//            listView.setOnItemClickListener { adapterView, view, i, l ->
//                val intent = Intent(context, MapsActivity::class.java)
//                val lat: Double;
//                val lng: Double;
//                if (i == 0) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183376567105734
//                    lng = 74.9335084245757
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                } else if (i == 1) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183376567105734
//                    lng = 74.9335084245757
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                } else if (i == 2) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183575358800482
//                    lng = 74.93880910912642
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                }
//            }
//        }
//        else if (position == 7) {
////            hostel
//            val listView = holder.binding.listView;
//            val names = arrayOf("NET Boys Main", "", "");
//
//            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                context, android.R.layout.simple_list_item_1, names
//            )
//
//            listView.adapter = arrayAdapter
//            listView.setOnItemClickListener { adapterView, view, i, l ->
//                val intent = Intent(context, MapsActivity::class.java)
//                val lat: Double;
//                val lng: Double;
//                if (i == 0) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183376567105734
//                    lng = 74.9335084245757
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                } else if (i == 1) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183376567105734
//                    lng = 74.9335084245757
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                } else if (i == 2) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183575358800482
//                    lng = 74.93880910912642
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                }
//            }
//        }
//        else if (position == 8) {
////            xerox
//            val listView = holder.binding.listView;
//            val names = arrayOf("1", "2", "3");
//
//            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                context, android.R.layout.simple_list_item_1, names
//            )
//
//            listView.adapter = arrayAdapter
//            listView.setOnItemClickListener { adapterView, view, i, l ->
//                val intent = Intent(context, MapsActivity::class.java)
//                val lat: Double;
//                val lng: Double;
//                if (i == 0) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183376567105734
//                    lng = 74.9335084245757
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                } else if (i == 1) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183376567105734
//                    lng = 74.9335084245757
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                } else if (i == 2) {
//                    Toast.makeText(context, "Item selected " + names[i], Toast.LENGTH_SHORT).show();
////            var latlng = LatLng(16.099108, -22.812924);
//                    lat = 13.183575358800482
//                    lng = 74.93880910912642
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//                    context.startActivity(intent)
//                }
//            }
//        }
    }
    override fun getItemCount(): Int {
        return images.size
    }
}