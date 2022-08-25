//package com.example.nmamitmap
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import android.widget.ListView
//import android.widget.Toast
//
//class SearchTabActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_search_tab)
//
//        val listView = findViewById<ListView>(R.id.listView);
//        val names = arrayOf("idk", "man");
//
//        val arrayAdapter: ArrayAdapter<String> =
//            ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
//
//        listView.adapter = arrayAdapter;
//
//        listView.setOnItemClickListener { adapterView, view, i, l ->
//            Toast.makeText(this, "Item Selected" + names[i], Toast.LENGTH_LONG).show();
////TODO: show marker and hide other markers
//            val intent = Intent(this, MapsActivity::class.java).apply {
////                putExtra(EXTRA_MESSAGE, message)
//            }
//            startActivity(intent)
//        }
//    }
//}

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

//viewpager2 tab layout
class SearchTabActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySearchTab2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTab2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val images = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
        )

        var tabNo: Int = 0;

//        val adapter = ViewPagerAdapter2(this, images)
//        binding.viewPager.adapter = adapter
//        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//        binding.tabLayout.tabMode
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            if (position == 0) tab.text = "Food"
//            else if (position == 1) tab.text = "Garden/Hangout"
//            else if (position == 2) tab.text = "Xerox/Cyber"
//            else if (position == 3) tab.text = "Medicine"
//            else if (position == 4) tab.text = "Sport"
//            else if (position == 5) tab.text = "General store"
//            else if (position == 6) tab.text = "Hostel"
//            else if (position == 7) tab.text = "Fuel/Garage"
//
////            tab.text = "Tab ${position+1}"
////            tab.badge
////            tabNo = position
//        }.attach()


//
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
////                Toast.makeText(this@SearchTabActivity, "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
////                Toast.makeText(this@SearchTabActivity, "Unselected ${tab?.text}", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
////                Toast.makeText(this@SearchTabActivity, "Reselected ${tab?.text}", Toast.LENGTH_SHORT).show()
//            }
//        })

//        viewPager.beginFakeDrag()
//        viewPager.fakeDragBy(-10f)
//        viewPager.endFakeDrag()


        val listView = binding.listView;

        val names =
            arrayOf("Sanmathi Cafe", "Shabari", "Harshitha", "Shisha Food Court", "Oven Fresh");

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, names
        )

        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
            val intent = Intent(this, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
            var lat = 13.18331782186854
            var lng = 74.93365618783577
            intent.putExtra("key-lat", lat);
            intent.putExtra("key-lng", lng);
            intent.putExtra("viaIntent", 1);

            this.startActivity(intent)
        }


        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->
            // Responds to child chip checked/unchecked
//            Toast.makeText(
//                this@SearchTabActivity2,
//                "hi${checkedId.size}${checkedId.get(0)}",
//                Toast.LENGTH_LONG
//            ).show()
//            Toast.makeText(this@SearchTabActivity2, "hi${binding.chipFood.id}", Toast.LENGTH_LONG).show()

//            1
            if (checkedId.contains(binding.chipFood.id)) {
                //            food
                val listView = binding.listView;
                val names = arrayOf(
                    "Sanmathi Cafe",
                    "Shabari",
                    "Harshitha",
                    "Shisha Food Court",
                    "Oven Fresh",
                );

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            2
            if (checkedId.contains(binding.chipHangout.id)) {
                //            hangout
                val listView = binding.listView;
                val names = arrayOf("Sanmathi garden");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

            //            2
            if (checkedId.contains(binding.chipStore.id)) {
                //            shop
                val listView = binding.listView;
                val names = arrayOf("Shabari store");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            4
            if (checkedId.contains(binding.chipMedicine.id)) {
                //            medicine
                val listView = binding.listView;
                val names = arrayOf("gznitte");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            5
            if (checkedId.contains(binding.chipPrint.id)) {
                //            print
                val listView = binding.listView;
                val names = arrayOf("Ground Zero cyber");

                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                    this, android.R.layout.simple_list_item_1, names
                )

                listView.adapter = arrayAdapter
                listView.setOnItemClickListener { adapterView, view, i, l ->
                    Toast.makeText(this, names[i] + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
//            var latlng = LatLng(16.099108, -22.812924);
                    var lat = 13.18331782186854
                    var lng = 74.93365618783577
                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            6
//            7
//            8
//            9
        }

        binding.bottomNavigationView.selectedItemId = R.id.miOut
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miMap -> {
                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                }
//                R.id.miProfile -> setCurrentFragment(secondFragment)
//                R.id.miProfile -> startActivity(intent)
                R.id.miIn -> {
                    val intent = Intent(this, SearchTabActivity::class.java)
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
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