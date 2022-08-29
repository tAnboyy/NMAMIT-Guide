package com.example.nmamitmap

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nmamitmap.databinding.ActivitySearchTabBinding
import com.example.nmamitmap.teacher.Teacher
import kotlin.collections.ArrayList

class SearchTabActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchTabBinding

    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private val teachers: List<Teacher> by lazy {
        TeachersReader(this).read()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTabBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listView = binding.listView;

        val foods = arrayListOf<Place>();
        places.forEach { place ->
            if (place.cat == "food" && place.inout == "in") foods.add(place)
        }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

        listView.adapter = PlaceListAdapter(this, foods as ArrayList<Place>)

        listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "food" && place.index == i + 1) {
            Toast.makeText(this, foods[i].name + " selected", Toast.LENGTH_SHORT).show();
            val intent = Intent(this, MapsActivity::class.java)
            val lat = foods[i].latLng.latitude
            val lng = foods[i].latLng.longitude

            intent.putExtra("key-lat", lat);
            intent.putExtra("key-lng", lng);
            intent.putExtra("viaIntent", 1);

            this.startActivity(intent)
        }

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->

//            1 FOOD
            foods.removeAll(foods.toSet())
            if (checkedId.contains(binding.chipFood.id)) {
                places.forEach { place ->
                    if (place.cat == "food" && place.inout == "in") foods.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, foods as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        foods.forEach { food ->
                            if (food.name == query ) searchResult.add(food)
                        }
                        listView.adapter = PlaceListAdapter(this@SearchTabActivity, searchResult as ArrayList<Place>)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        foods.forEach { food ->
                            if (newText != null) {
                                if (food.name.lowercase().contains(newText.lowercase())) searchResult.add(food)
                            }
                        }
                        listView.adapter = PlaceListAdapter(this@SearchTabActivity, searchResult as ArrayList<Place>)
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "food" && place.index == i + 1) {
                    Toast.makeText(this, foods[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = foods[i].latLng.latitude
                    val lng = foods[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            2 BLOCK
            if (checkedId.contains(binding.chipBlock.id)) {

                val blocks = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "block") blocks.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, blocks as ArrayList<Place>)
                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, blocks[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = blocks[i].latLng.latitude
                    val lng = blocks[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            3 TEACHER
            if (checkedId.contains(binding.chipTeacher.id)) {

//                var sortedList = teachers.sortedWith(compareBy({
//                    it.firstName
//                }))
//                teachers.sortedWith(Comparator { x, y -> x.name.compareTo(y.name)})

//                teachers.sortBy { it.name }
//                Toast.makeText(this, teachers[0].name + " selected", Toast.LENGTH_SHORT).show();

//                val sortedTeachers = teachers.sortedWith(Comparator.naturalOrder<>())

//                Collections.sort(teachers)

//                val teachers = teachers.sortedBy { teacher -> teacher.name }
//                val sortedTeachers : ArrayList<Teacher> = teachers.sortedBy { teacher -> teacher.name} as ArrayList<Teacher>

                listView.adapter = TeacherListAdapter(this, teachers as ArrayList<Teacher>)
//                (listView.adapter as TeacherListAdapter).notifyDataSetChanged()

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Teacher>();
                        teachers.forEach { teacher ->
                            if (teacher.name == query) searchResult.add(teacher)
                        }
                        listView.adapter = TeacherListAdapter(this@SearchTabActivity, searchResult as ArrayList<Teacher>)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Teacher>();
                        teachers.forEach { teacher ->
                            if (newText?.let { teacher.name.contains(it) } == true) searchResult.add(teacher)
                        }
                        listView.adapter = TeacherListAdapter(this@SearchTabActivity, searchResult as ArrayList<Teacher>)
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

                    Toast.makeText(this, teachers[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = teachers[i].latLng.latitude
                    val lng = teachers[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);
                    intent.putExtra("title", teachers[i].name);
                    intent.putExtra("snippet", teachers[i].block + " " + teachers[i].floor);

                    this.startActivity(intent)
                }

            }

//            4 HALL
//            5 LIBRARY/STUDY
//            6 XEROX/PRINT
//            7 OTHERS
        }

        binding.bottomNavigationView.selectedItemId = R.id.miIn
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.miMap -> {
                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    )
                }
                R.id.miOut -> {
                    val intent = Intent(this, SearchTabActivity2::class.java)
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
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }
}


