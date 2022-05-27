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

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.nmamitmap.databinding.ActivitySearchTabBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//viewpager2 tab layout
class SearchTabActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTabBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val images = listOf(R.drawable.ic_launcher_background, R.drawable.ic_launcher_background)

        var tabNo: Int = 0;

        val adapter = ViewPagerAdapter(this, images)
        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Tab ${position+1}"
//            tab.badge
//            tabNo = position
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@SearchTabActivity, "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@SearchTabActivity, "Unselected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@SearchTabActivity, "Reselected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }
        })

//        viewPager.beginFakeDrag()
//        viewPager.fakeDragBy(-10f)
//        viewPager.endFakeDrag()

        binding.bottomNavigationView.selectedItemId = R.id.miPlaces
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val intent = Intent(this, MapsActivity::class.java)
            when(it.itemId) {
                R.id.miMap -> startActivity(intent)
//                R.id.miProfile -> setCurrentFragment(secondFragment)
//                R.id.miProfile -> startActivity(intent)
            }
            true
        }
    }
}