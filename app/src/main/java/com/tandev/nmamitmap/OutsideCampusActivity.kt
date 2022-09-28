package com.tandev.nmamitmap

import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.example.nmamitmap.Place
import com.example.nmamitmap.PlacesReader
import com.example.nmamitmap.R
import com.example.nmamitmap.databinding.ActivitySearchTab2Binding
import java.security.AccessController.getContext

class SearchTabActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySearchTab2Binding

    public var LOCATION_PERMISSION_REQUEST = 0

    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private lateinit var toolbar: Toolbar

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.navSearch) {
////            Toast.makeText(this, "clicked Search", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, SearchTabActivity::class.java)
//            startActivity(
//                intent,
//                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
//            )
//        }
//        else if (item.itemId == R.id.navInfo) { }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        //hide Toolbar
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        supportActionBar?.hide()

        binding = ActivitySearchTab2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        toolbar = findViewById(R.id.toolbar)
//        toolbar.setTitle("")
//        setSupportActionBar(toolbar)


//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )


//        val nightModeFlags: Int = getContext().getResources().getConfiguration().uiMode and
//                Configuration.UI_MODE_NIGHT_MASK
//        when (nightModeFlags) {
//            Configuration.UI_MODE_NIGHT_YES -> doStuff()
//            Configuration.UI_MODE_NIGHT_NO -> doStuff()
//            Configuration.UI_MODE_NIGHT_UNDEFINED -> doStuff()
//        }

        var searchView: SearchView

        var listView: ListView = binding.listView


        if (this.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            binding.searchView.visibility = View.GONE
            binding.searchViewDark.visibility = View.VISIBLE
            searchView = binding.searchViewDark

            listView = binding.listViewDark

            binding.listView.visibility = View.GONE
            binding.listViewDark.visibility = View.VISIBLE
        } else {
            binding.searchView.visibility = View.VISIBLE
            binding.searchViewDark.visibility = View.GONE
            searchView = binding.searchView

            listView = binding.listView
            binding.listView.visibility = View.VISIBLE
            binding.listViewDark.visibility = View.GONE
        }


        val foods = arrayListOf<Place>();
        places.forEach { place ->
            if (place.cat == "food" && place.inout == "out") foods.add(place)
        }
        listView.adapter = PlaceListAdapter(this, foods as ArrayList<Place>)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                val searchResult = arrayListOf<Place>();
                foods.forEach { food ->
                    if (food.name.lowercase()
                            .contains(query.toString().lowercase())
                    ) searchResult.add(food)
                }
                listView.adapter =
                    PlaceListAdapter(this@SearchTabActivity2, searchResult as ArrayList<Place>)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchResult = arrayListOf<Place>();
                foods.forEach { food ->
                    if (newText != null) {
                        if (food.name.lowercase()
                                .contains(newText.lowercase())
                        ) searchResult.add(
                            food
                        )
                    }
                }
                listView.adapter =
                    PlaceListAdapter(this@SearchTabActivity2, searchResult as ArrayList<Place>)
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

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedId ->

//            1 FOOD
            if (checkedId.contains(binding.chipFood.id)) {
//                val listView = binding.listView;

                val foods = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "food" && place.inout == "out") foods.add(place)
                }

                listView.adapter = PlaceListAdapter(this, foods as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        foods.forEach { food ->
                            if (food.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(food)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
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
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

                    Toast.makeText(this, foods[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = foods[i].latLng.latitude
                    val lng = foods[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }

//            2 GARDEN/HANGOUT
            if (checkedId.contains(binding.chipHangout.id)) {
//                val listView = binding.listView;

                val hangouts = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "hangout") hangouts.add(place)
                }

                listView.adapter = PlaceListAdapter(
                    this,
                    hangouts as ArrayList<Place> /* = java.util.ArrayList<com.example.nmamitmap.Place> */
                )

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        hangouts.forEach { hangout ->
                            if (hangout.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(hangout)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        hangouts.forEach { hangout ->
                            if (newText != null) {
                                if (hangout.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(hangout)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

                    hangouts.forEach { place ->
                        Toast.makeText(this, hangouts[i].name + " selected", Toast.LENGTH_SHORT)
                            .show();
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

//            3 SHOP
            if (checkedId.contains(binding.chipShop.id)) {
//                val listView = binding.listView;

                val shops = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "shop") shops.add(place)
                }

                listView.adapter = PlaceListAdapter(this, shops)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        shops.forEach { shop ->
                            if (shop.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(shop)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        shops.forEach { shop ->
                            if (newText != null) {
                                if (shop.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(shop)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

                    places.forEach { place ->
                        Toast.makeText(this, shops[i].name + " selected", Toast.LENGTH_SHORT)
                            .show();
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

//            4 XEROX/CYBER
            if (checkedId.contains(binding.chipPrint.id)) {

                val prints = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "print" && place.inout == "out") prints.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, prints as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        prints.forEach { print ->
                            if (print.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(print)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
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
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, prints[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = prints[i].latLng.latitude
                    val lng = prints[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            5 MEDICINE
            if (checkedId.contains(binding.chipMedicine.id)) {

                val meds = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "med") meds.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, meds as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        meds.forEach { stop ->
                            if (stop.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(stop)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        meds.forEach { stop ->
                            if (newText != null) {
                                if (stop.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(stop)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, meds[i].name + " selected", Toast.LENGTH_SHORT).show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = meds[i].latLng.latitude
                    val lng = meds[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            6 HOSTEL
            if (checkedId.contains(binding.chipHostel.id)) {

                val hostels = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "hostel") hostels.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, hostels as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        hostels.forEach { stop ->
                            if (stop.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(stop)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        hostels.forEach { stop ->
                            if (newText != null) {
                                if (stop.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(stop)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, hostels[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = hostels[i].latLng.latitude
                    val lng = hostels[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            7 SPORT/GROUND
            if (checkedId.contains(binding.chipSport.id)) {

                val sports = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "sport") sports.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, sports as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        sports.forEach { stop ->
                            if (stop.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(stop)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        sports.forEach { stop ->
                            if (newText != null) {
                                if (stop.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(stop)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, sports[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = sports[i].latLng.latitude
                    val lng = sports[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            8 FUEL/GARAGE
            if (checkedId.contains(binding.chipVehicle.id)) {

                val vehicles = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "vehicle") vehicles.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, vehicles as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        vehicles.forEach { stop ->
                            if (stop.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(stop)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        vehicles.forEach { stop ->
                            if (newText != null) {
                                if (stop.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(stop)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, vehicles[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = vehicles[i].latLng.latitude
                    val lng = vehicles[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            9 BUS/AUTO
            if (checkedId.contains(binding.chipBusAuto.id)) {

                val stops = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "stop") stops.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, stops as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        stops.forEach { stop ->
                            if (stop.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(stop)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchResult = arrayListOf<Place>();
                        stops.forEach { stop ->
                            if (newText != null) {
                                if (stop.name.lowercase()
                                        .contains(newText.lowercase())
                                ) searchResult.add(stop)
                            }
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, stops[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
                    val intent = Intent(this, MapsActivity::class.java)
                    val lat = stops[i].latLng.latitude
                    val lng = stops[i].latLng.longitude

                    intent.putExtra("key-lat", lat);
                    intent.putExtra("key-lng", lng);
                    intent.putExtra("viaIntent", 1);

                    this.startActivity(intent)
                }
            }
//            10 OTHERS
            if (checkedId.contains(binding.chipOthers.id)) {

                val others = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "other" && place.inout == "out") others.add(place)
                }

//                val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
//                    this, android.R.layout.simple_list_item_1, names
//                )

                listView.adapter = PlaceListAdapter(this, others as ArrayList<Place>)

                searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        others.forEach { other ->
                            if (other.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(other)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
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
                            this@SearchTabActivity2,
                            searchResult as ArrayList<Place>
                        )
                        return false
                    }
                })

                listView.setOnItemClickListener { adapterView, view, i, l ->

//                    places.forEach { place ->
//                        if (place.cat == "block" && place.index == i + 1) {
                    Toast.makeText(this, others[i].name + " selected", Toast.LENGTH_SHORT)
                        .show();
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
//            if (checkedId.contains(binding.chipGlobal.id)) {
//
//                listView.adapter = PlaceListAdapter(this, places as ArrayList<Place>)
//
//                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        searchView.clearFocus()
//                        val searchResult = arrayListOf<Place>();
//                        places.forEach { place ->
//                            if (place.name.lowercase()
//                                    .contains(query.toString().lowercase())
//                            ) searchResult.add(place)
//                        }
//                        listView.adapter = PlaceListAdapter(
//                            this@SearchTabActivity2,
//                            searchResult as ArrayList<Place>
//                        )
//                        return false
//                    }
//
//                    override fun onQueryTextChange(newText: String?): Boolean {
//                        val searchResult = arrayListOf<Place>();
//                        places.forEach { place ->
//                            if (newText != null) {
//                                if (place.name.lowercase()
//                                        .contains(newText.lowercase())
//                                ) searchResult.add(place)
//                            }
//                        }
//                        listView.adapter = PlaceListAdapter(
//                            this@SearchTabActivity2,
//                            searchResult as ArrayList<Place>
//                        )
//                        return false
//                    }
//                })
//
//                listView.setOnItemClickListener { adapterView, view, i, l ->
//                    Toast.makeText(this, places[i].name + " selected", Toast.LENGTH_SHORT).show();
//                    val intent = Intent(this, MapsActivity::class.java)
//                    val lat = places[i].latLng.latitude
//                    val lng = places[i].latLng.longitude
//
//                    intent.putExtra("key-lat", lat);
//                    intent.putExtra("key-lng", lng);
//                    intent.putExtra("viaIntent", 1);
//
//                    this.startActivity(intent)
//                }
//            }
        }


        binding.bottomNavigationView.selectedItemId = R.id.miOut
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.miMap -> {
                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                    )
                }
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