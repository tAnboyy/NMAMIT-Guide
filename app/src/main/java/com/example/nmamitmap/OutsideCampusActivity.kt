package com.example.nmamitmap

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView

import com.example.nmamitmap.databinding.ActivitySearchTab2Binding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class SearchTabActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySearchTab2Binding

    public var LOCATION_PERMISSION_REQUEST = 0

    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    fun getPermissions() {
        Dexter.withActivity(this)
            .withPermissions(
//                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_CONTACTS
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                        Toast.makeText(
                            this@SearchTabActivity2,
                            "All the permissions are granted...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied) {
                        Toast.makeText(
                            this@SearchTabActivity2,
                            "All the permissions are permanently denied...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    list: List<PermissionRequest?>?,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(applicationContext, "Error occurred! ", Toast.LENGTH_SHORT).show()
            }
            .onSameThread().check()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchTab2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listView = binding.listView;

        getPermissions()

        val foods = arrayListOf<Place>();
        places.forEach { place ->
            if (place.cat == "food" && place.inout == "out") foods.add(place)
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
                val listView = binding.listView;

                val foods = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "food" && place.inout == "out") foods.add(place)
                }

                listView.adapter = PlaceListAdapter(this, foods as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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
                val listView = binding.listView;

                val hangouts = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "hangout") hangouts.add(place)
                }

                listView.adapter = PlaceListAdapter(this, hangouts)

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                    places.forEach { place ->
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
                val listView = binding.listView;

                val shops = arrayListOf<Place>();
                places.forEach { place ->
                    if (place.cat == "shop") shops.add(place)
                }

                listView.adapter = PlaceListAdapter(this, shops)

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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

                binding.searchView.setOnQueryTextListener(object :
                    SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
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
            if (checkedId.contains(binding.chipGlobal.id)) {

                listView.adapter = PlaceListAdapter(this, places as ArrayList<Place>)

                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        binding.searchView.clearFocus()
                        val searchResult = arrayListOf<Place>();
                        places.forEach { place ->
                            if (place.name.lowercase()
                                    .contains(query.toString().lowercase())
                            ) searchResult.add(place)
                        }
                        listView.adapter = PlaceListAdapter(
                            this@SearchTabActivity2,
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
                            this@SearchTabActivity2,
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