package com.tandev.nmamitmap

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tandev.nmamitmap.Place
import com.tandev.nmamitmap.PlacesReader
import com.tandev.nmamitmap.R
import com.tandev.nmamitmap.TeachersReader
import com.tandev.nmamitmap.databinding.ActivitySearchTabBinding
import com.tandev.nmamitmap.teacher.Teacher
import kotlin.collections.ArrayList

class SearchTabActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchTabBinding

    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private val teachers: List<Teacher> by lazy {
        TeachersReader(this).read()
    }
    private lateinit var toolbar: Toolbar

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.navSearch) {
//            Toast.makeText(this, "clicked Search", Toast.LENGTH_LONG).show()
            val intent = Intent(this, SearchTabActivity::class.java)
            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        } else if (item.itemId == R.id.navInfo) {

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        //hide Toolbar
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        supportActionBar?.hide()

        binding = ActivitySearchTabBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)

        var searchView: SearchView

        if (this.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            binding.searchView.visibility = View.GONE
            binding.searchViewDark.visibility = View.VISIBLE
            searchView = binding.searchViewDark
        } else {
            binding.searchView.visibility = View.VISIBLE
            binding.searchViewDark.visibility = View.GONE
            searchView = binding.searchView
        }

//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                binding.searchView.clearFocus()
//                val searchResult = arrayListOf<Place>();
//                foods.forEach { food ->
//                    if (food.name.lowercase()
//                            .contains(query.toString().lowercase())
//                    ) searchResult.add(food)
//                }
//                listView.adapter =
//                    PlaceListAdapter(this@SearchTabActivity, searchResult as ArrayList<Place>)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                val searchResult = arrayListOf<Place>();
//                foods.forEach { food ->
//                    if (newText != null) {
//                        if (food.name.lowercase().contains(newText.lowercase())) searchResult.add(
//                            food
//                        )
//                    }
//                }
//                listView.adapter =
//                    PlaceListAdapter(this@SearchTabActivity, searchResult as ArrayList<Place>)
//                return false
//            }
//        })
//
//        listView.setOnItemClickListener { adapterView, view, i, l ->
//
////                    places.forEach { place ->
////                        if (place.cat == "food" && place.index == i + 1) {
//            Toast.makeText(this, foods[i].name + " selected", Toast.LENGTH_SHORT).show();
//            val intent = Intent(this, MapsActivity::class.java)
//            val lat = foods[i].latLng.latitude
//            val lng = foods[i].latLng.longitude
//
//            intent.putExtra("key-lat", lat);
//            intent.putExtra("key-lng", lng);
//            intent.putExtra("viaIntent", 1);
//
//            this.startActivity(intent)
//        }

        val listView = binding.listView;
        listView.adapter = PlaceListAdapter(this, places as ArrayList<Place>)
        searchView.hasFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                val searchResult = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.name.lowercase()
                            .contains(query.toString().lowercase())
                    ) searchResult.add(place)
                }
                listView.adapter = PlaceListAdapter(
                    this@SearchTabActivity,
                    searchResult as ArrayList<Place>
                )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchResult = arrayListOf<Place>();
                places.forEach { place ->
                    if (newText != null) {
                        if (place.name.lowercase()
                                .contains(newText.lowercase())
                        ) searchResult.add(place)
                    }
                }
                listView.adapter = PlaceListAdapter(
                    this@SearchTabActivity,
                    searchResult as ArrayList<Place>
                )
                return false
            }
        })

        listView.setOnItemClickListener { adapterView, view, i, l ->

            Toast.makeText(this, places[i].name + " selected", Toast.LENGTH_SHORT).show();
            val intent = Intent(this, MapsActivity::class.java)
            val lat = places[i].latLng.latitude
            val lng = places[i].latLng.longitude

            intent.putExtra("key-lat", lat);
            intent.putExtra("key-lng", lng);
            intent.putExtra("viaIntent", 1);

            this.startActivity(intent)
        }


        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->

//            1 FOOD
            val foods = arrayListOf<Place>();
            places.forEach { place ->
                if (place.cat == "food" && place.inout == "in") foods.add(place)
            }

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
                            if (food.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(food)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        foods.forEach { food ->
                            if (newText != null) {
                                if (food.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(food)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
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

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        blocks.forEach { block ->
                            if (block.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(block)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        blocks.forEach { block ->
                            if (newText != null) {
                                if (block.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(block)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

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
                            if (teacher.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(teacher)
                        }
                        listView.adapter = TeacherListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Teacher>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Teacher>();
                        teachers.forEach { teacher ->
                            if (newText?.let {
                                    teacher.name.lowercase().contains(it.lowercase())
                                } == true) searchResult.add(teacher)
                            else if (newText?.let {
                                    teacher.branch.lowercase().contains(it.lowercase())
                                } == true) searchResult.add(teacher)
                            else if (newText?.let {
                                    teacher.block.lowercase().contains(it.lowercase())
                                } == true) searchResult.add(teacher)
                            else if (newText?.let {
                                    teacher.floor.lowercase().contains(it.lowercase())
                                } == true) searchResult.add(teacher)
                        }
                        listView.adapter = TeacherListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Teacher>
                        )
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
            if (checkedId.contains(binding.chipHall.id)) {

                val halls = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "hall") halls.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, halls as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        halls.forEach { hall ->
                            if (hall.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(hall)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        halls.forEach { hall ->
                            if (newText != null) {
                                if (hall.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(hall)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, halls[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = halls[i].latLng.latitude
                    val lng = halls[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            5 LIBRARY
            if (checkedId.contains(binding.chipLibrary.id)) {

                val libraries = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "library") libraries.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, libraries as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        libraries.forEach { library ->
                            if (library.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(library)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        libraries.forEach { library ->
                            if (newText != null) {
                                if (library.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(library)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, libraries[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = libraries[i].latLng.latitude
                    val lng = libraries[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            6 XEROX/PRINT
            if (checkedId.contains(binding.chipPrint.id)) {

                val prints = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "print" && place.inout == "in") prints.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, prints as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        prints.forEach { print ->
                            if (print.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(print)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        prints.forEach { print ->
                            if (newText != null) {
                                if (print.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(print)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, prints[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = prints[i].latLng.latitude
                    val lng = prints[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            7 OTHERS
            if (checkedId.contains(binding.chipOthers.id)) {

                val others = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "other" && place.inout == "in") others.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, others as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        others.forEach { other ->
                            if (other.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(other)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        others.forEach { other ->
                            if (newText != null) {
                                if (other.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(other)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, others[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = others[i].latLng.latitude
                    val lng = others[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

            //            7 GLOBAL SEARCH
            if (checkedId.contains(binding.chipGlobal.id)) {

                listView.adapter = PlaceListAdapter(this, places as ArrayList<Place>)
                searchView.hasFocus()
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        places.forEach { place ->
                            if (place.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(place)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        places.forEach { place ->
                            if (newText != null) {
                                if (place.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(place)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

                    Toast.makeText(this, places[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = places[i].latLng.latitude
                    val lng = places[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.miIn
        binding.bottomNavigationView.setOnItemSelectedListener {
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


