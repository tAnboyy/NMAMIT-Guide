package com.example.nmamitmap

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.nmamitmap.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationRequest.create
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLngBounds

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
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
            getLocationUpdates()
            startLocationUpdates()
        }
        else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                mMap.isMyLocationEnabled = true
                getLocationAccess()
            }
            else {
                Toast.makeText(this, "User has not granted location access permission", Toast.LENGTH_LONG).show()
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

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            val intent = Intent(this, SearchTabActivity::class.java)
            when(it.itemId) {
//                R.id.miHome -> setCurrentFragment(firstFragment)
//                R.id.miProfile -> setCurrentFragment(secondFragment)
                R.id.miPlaces -> startActivity(intent)
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


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isZoomControlsEnabled = true


        var recievedLat = intent.getDoubleExtra("key-lat", 0.0)
        var recievedLng = intent.getDoubleExtra("key-lng", 0.0)

//        recievedLatLng?.let { CameraUpdateFactory.newLatLngZoom(it, 5f) }
//            ?.let { mMap.animateCamera(it) }
        var recievedLatLng = LatLng(recievedLat, recievedLng)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(recievedLatLng, 5f))
        mMap.addMarker(MarkerOptions().position(recievedLatLng).title("Marker in {TODO: send title w intent}"))
        mMap.isMyLocationEnabled = true

        if (intent.getIntExtra("disCur", 0) != 1) setUpMap()

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

    private fun setUpMap() {
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->

            if(location != null) {
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 5f))
            }
        }
    }
}