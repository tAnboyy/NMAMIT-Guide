package com.example.nmamitmap

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.nmamitmap.databinding.ActivitySearchTab2Binding
import com.example.nmamitmap.databinding.ActivitySearchTabBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SearchTabActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySearchTab2Binding

    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTab2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listView = binding.listView;

        val names = arrayListOf<String>();
        places.forEach { place ->
            if (place.cat == "food" && place.inout == "out") names.add(place.name)
        }

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, names
        )

        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->

            places.forEach { place ->
                if (place.cat == "food" && place.index == i + 1) {
                    Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = place.latLng.latitude
                    val lng = place.latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->

//            1 FOOD
            if (checkedId.contains(binding.chipFood.id)) {
                val listView = binding.listView;

                val names = arrayListOf<String>();
                places.forEach { place ->
                    if (place.cat == "food" && place.inout == "out") names.add(place.name)
                }

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->

                    places.forEach { place ->
                        if (place.cat == "food" && place.index == i + 1) {
                            Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                            val intent = Intent(this, MapsActivity::class.java)
                            val lat = place.latLng.latitude
                            val lng = place.latLng.longitude

                            intent.putExtra("key-lat", lat);
                            intent.putExtra("key-lng", lng);
                            intent.putExtra("viaIntent", 1);

                            this.startActivity(intent)
                        }
                    }
                }
            }

//            2 GARDEN/HANGOUT
            if (checkedId.contains(binding.chipHangout.id)) {
                val listView = binding.listView;

                val names = arrayListOf<String>();
                places.forEach { place ->
                    if (place.cat == "hangout") names.add(place.name)
                }

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->

                    places.forEach { place ->
                        if (place.cat == "hangout" && place.index == i + 1) {
                            Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                            val intent = Intent(this, MapsActivity::class.java)
                            val lat = place.latLng.latitude
                            val lng = place.latLng.longitude

                            intent.putExtra("key-lat", lat);
                            intent.putExtra("key-lng", lng);
                            intent.putExtra("viaIntent", 1);

                            this.startActivity(intent)
                        }
                    }
                }
            }

//            3 SHOP
            if (checkedId.contains(binding.chipShop.id)) {
                val listView = binding.listView;

                val names = arrayListOf<String>();
                places.forEach { place ->
                    if (place.cat == "shop") names.add(place.name)
                }

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->

                    places.forEach { place ->
                        if (place.cat == "shop" && place.index == i + 1) {
                            Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                            val intent = Intent(this, MapsActivity::class.java)
                            val lat = place.latLng.latitude
                            val lng = place.latLng.longitude

                            intent.putExtra("key-lat", lat);
                            intent.putExtra("key-lng", lng);
                            intent.putExtra("viaIntent", 1);

                            this.startActivity(intent)
                        }
                    }
                }
            }

//            4 XEROX/CYBER
//            5 MEDICINE
//            6 HOSTEL
//            7 SPORT/GROUND
//            8 FUEL/GARAGE
//            9 BUS/AUTO
//            10 OTHERS
        }

        binding.bottomNavigationView.selectedItemId = R.id.miOut
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miMap -> {
                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    )
                }
//                R.id.miProfile -> setCurrentFragment(secondFragment)
//                R.id.miProfile -> startActivity(intent)
                R.id.miIn -> {
                    val intent = Intent(this, SearchTabActivity::class.java)
                    startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    )
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.bottomNavigationView.selectedItemId = R.id.miMap

        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
}