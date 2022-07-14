package com.example.nmamitmap

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationRequest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.nmamitmap.databinding.ActivityMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.*

import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val LOCATION_PERMISSION_REQUEST = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private lateinit var lastLocation: Location

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
            getLocationUpdates()
            startLocationUpdates()
        } else
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST
            )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                mMap.isMyLocationEnabled = true
                getLocationAccess()
            } else {
                Toast.makeText(
                    this,
                    "User has not granted location access permission",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

//        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.selectedItemId = R.id.miMap
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
//                R.id.miHome -> setCurrentFragment(firstFragment)
//                R.id.miProfile -> setCurrentFragment(secondFragment)
                R.id.miIn -> {
                    val intent = Intent(this, SearchTabActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                R.id.miOut -> {
                    val intent = Intent(this, SearchTabActivity2::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }

            true
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private fun getLocationUpdates() {
//        locationRequest = LocationRequest.create().apply {
//            interval = 30000
//            fastestInterval = 20000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//        }

//         val locationRequest = LocationRequest.create().apply {
//            fastestInterval = 1000
//            interval = 1000
//            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//            smallestDisplacement = 1.0f
//        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    if (location != null) {
                        val latLng = LatLng(location.latitude, location.longitude)
                        val markerOptions = MarkerOptions().position(latLng)
                        mMap.addMarker(markerOptions)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    }
                }
            }
        }
    }

    private fun startLocationUpdates() {
//        fusedLocationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            null
//        )
    }

    private val COLOR_WHITE_ARGB = -0x1
    private val COLOR_DARK_GREEN_ARGB = -0xc771c4
    private val COLOR_LIGHT_GREEN_ARGB = -0x7e387c
    private val COLOR_DARK_ORANGE_ARGB = -0xa80e9
    private val COLOR_LIGHT_ORANGE_ARGB = -0x657db
    private val POLYGON_STROKE_WIDTH_PX = 4
    private val PATTERN_DASH_LENGTH_PX = 20
    private val PATTERN_GAP_LENGTH_PX = 20
    private val COLOR_BLACK_ARGB = -0x1000000
    private val DASH: PatternItem = Dash(PATTERN_DASH_LENGTH_PX.toFloat())
    private val DOT: PatternItem = Dot()
    private val GAP: PatternItem = Gap(PATTERN_GAP_LENGTH_PX.toFloat())

    // Create a stroke pattern of a gap followed by a dash.
    private val PATTERN_POLYGON_ALPHA = listOf(GAP, DASH)

    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
    private val PATTERN_POLYGON_BETA = listOf(DOT, GAP, DASH, GAP)

    /**
     * Styles the polygon, based on type.
     * @param polygon The polygon object that needs styling.
     */
    private fun stylePolygon(polygon: Polygon) {
        // Get the data object stored with the polygon.
        val type = polygon.tag?.toString() ?: ""
        var pattern: List<PatternItem>? = null
        var strokeColor = COLOR_BLACK_ARGB
        var fillColor = COLOR_WHITE_ARGB
        when (type) {
            "alpha" -> {
                // Apply a stroke pattern to render a dashed line, and define colors.
                pattern = PATTERN_POLYGON_ALPHA
                strokeColor = COLOR_DARK_GREEN_ARGB
                fillColor = COLOR_LIGHT_GREEN_ARGB
            }
            "beta" -> {
                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
                pattern = PATTERN_POLYGON_BETA
                strokeColor = COLOR_DARK_ORANGE_ARGB
                fillColor = COLOR_LIGHT_ORANGE_ARGB
            }
        }
        polygon.strokePattern = pattern
        polygon.strokeWidth = POLYGON_STROKE_WIDTH_PX.toFloat()
        polygon.strokeColor = strokeColor
//        polygon.fillColor = fillColor
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true

        // Add polygons to indicate areas on the map.
        val polygon1 = googleMap.addPolygon(
            PolygonOptions()
                .clickable(true)
                .add(
                    LatLng(13.18315798008595, 74.93638211605136),
                    LatLng(13.183936212408991, 74.93477010841427),
                    LatLng(13.183951881491984, 74.93276113384267),
                    LatLng(13.182200696515679, 74.93257012883282),
                    LatLng(13.182248452235134, 74.93551740212783)
                )
        )
        // Store a data object with the polygon, used here to indicate an arbitrary type.
        polygon1.tag = "alpha"
        // Style the polygon.
        stylePolygon(polygon1)


        if (intent.getIntExtra("viaIntent", 0) != 1) setUpMap()
        else if (intent.getIntExtra("viaIntent", 0) == 1) {
            var recievedLat = intent.getDoubleExtra("key-lat", 0.0)
            var recievedLng = intent.getDoubleExtra("key-lng", 0.0)

            val recievedTitle = intent.getStringExtra("title")
            val recievedSnippet = intent.getStringExtra("snippet")
//        recievedLatLng?.let { CameraUpdateFactory.newLatLngZoom(it, 5f) }
//            ?.let { mMap.animateCamera(it) }
            var recievedLatLng = LatLng(recievedLat + 0.00007, recievedLng)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(recievedLatLng, 18f))
            mMap.addMarker(
                MarkerOptions().position(recievedLatLng)
                    .title(recievedTitle).snippet(recievedSnippet)
            )

            mMap.isMyLocationEnabled = true
            fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

                if (location != null) {
                    lastLocation = location
                    val currentLatLong = LatLng(location.latitude, location.longitude)
//                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 5f))
                }
            }

            addMarkers(mMap)
            mMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
        }

        mMap.isMyLocationEnabled = true

        googleMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this, R.raw.style_json
            )
        );

//        val zoomLevel = 20f
//        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(13.18332846270605, 74.93347943062099)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))
//        getLocationAccess()


// Create a LatLngBounds that includes the city of Adelaide in Australia.
        val adelaideBounds = LatLngBounds(
            LatLng(13.182073899028802, 74.93021715948936),  // SW bounds
            LatLng(13.189073551405391, 74.94063947402205) // NE bounds
        )

// Constrain the camera target to the Adelaide bounds.
        mMap.setLatLngBoundsForCameraTarget(adelaideBounds)

        mMap.setMinZoomPreference(16.0f)
        mMap.setMaxZoomPreference(20.0f)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setUpMap() {
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if (location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 5f))
            }
        }

        addMarkers(mMap)
        mMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(this))
    }


    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    private val bicycleIcon: BitmapDescriptor by lazy {
        val color =
            ContextCompat.getColor(this, androidx.appcompat.R.color.primary_dark_material_dark)
        BitmapHelper.vectorToBitmap(this, R.drawable.ic_directions_bike_black_24dp, color)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun addMarkers(googleMap: GoogleMap) {
//        // Create a LatLngBounds that includes the city of Adelaide in Australia.
//        val adelaideBounds = LatLngBounds(
//            LatLng(-35.0, 138.58),  // SW bounds
//            LatLng(-34.9, 138.61) // NE bounds
//        )
//        // Constrain the camera target to the Adelaide bounds.
//        googleMap.setLatLngBoundsForCameraTarget(adelaideBounds)

        places.forEach { place ->
            if (place.cat == "food") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("Marker in Sydney")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_burger))
                )
                marker?.tag = place
            }
            if (place.cat == "block") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_building))
                )
                marker?.tag = place
            }
            if (place.cat == "block2") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_building2))
                )
                marker?.tag = place
            }
            if (place.cat == "hall") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_stage))
                )
                marker?.tag = place
            }
            if (place.cat == "print") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_print))
                )
                marker?.tag = place
            }
            if (place.cat == "hangout") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_park))
                )
                marker?.tag = place
            }
            if (place.cat == "med") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_medicine))
                )
                marker?.tag = place
            }
            if (place.cat == "sport") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_stadium))
                )
                marker?.tag = place
            }

            if (place.cat == "store") {
                val marker = googleMap.addMarker(
                    MarkerOptions().position(place.latLng).title("LC block")
                        .icon(bitmapDescriptorFromVector(this, R.drawable.ic_store))
                )
                marker?.tag = place
            }

//            val marker = googleMap.addMarker(
//                MarkerOptions().position(place.latLng).title("LC block")
//                    .icon(bitmapDescriptorFromVector(this, R.drawable.books))
//            )
//            marker?.tag = place

//            val marker = googleMap.addMarker(
//                MarkerOptions()
//                    .title(place.name)
//                    .position(place.latLng)
//                    .icon(bicycleIcon)
//            )

            // Set place as the tag on the marker object so it can be referenced within
            // MarkerInfoWindowAdapter
//            marker?.tag = place
        }
    }

    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight()
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight(),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}