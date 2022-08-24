package com.example.nmamitmap

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.location.LocationRequest
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nmamitmap.databinding.ActivityMapsBinding
import com.example.nmamitmap.databinding.FragmentFirstBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class FirstFragment : Fragment(R.layout.fragment_first) {
//
//    private lateinit var mMap: GoogleMap
//    private lateinit var binding: FragmentFirstBinding
//
//    private val LOCATION_PERMISSION_REQUEST = 1
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var locationRequest: LocationRequest
//    private lateinit var locationCallback: LocationCallback
//
//    private lateinit var lastLocation: Location
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding = FragmentFirstBinding.inflate(layoutInflater)
////        setContentView(binding.root)
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
////        val mapFragment = supportFragmentManager
////            .findFragmentById(R.id.map) as SupportMapFragment
////        mapFragment.getMapAsync(this)
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
//
////        setCurrentFragment(firstFragment)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//
//    fun getLocationUpdates() {
////        locationRequest = LocationRequest.create().apply {
////            interval = 30000
////            fastestInterval = 20000
////            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
////        }
//
////         val locationRequest = LocationRequest.create().apply {
////            fastestInterval = 1000
////            interval = 1000
////            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
////            smallestDisplacement = 1.0f
////        }
//
//        locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult) {
//                if (locationResult.locations.isNotEmpty()) {
//                    val location = locationResult.lastLocation
//                    if (location != null) {
//                        val latLng = LatLng(location.latitude, location.longitude)
//                        val markerOptions = MarkerOptions().position(latLng)
//                        mMap.addMarker(markerOptions)
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
//                    }
//                }
//            }
//        }
//    }
//
//    private fun startLocationUpdates() {
////        fusedLocationClient.requestLocationUpdates(
////            locationRequest,
////            locationCallback,
////            null
////        )
//    }
//
//    private val COLOR_WHITE_ARGB = -0x1
//    private val COLOR_DARK_GREEN_ARGB = -0xc771c4
//    private val COLOR_LIGHT_GREEN_ARGB = -0x7e387c
//    private val COLOR_DARK_ORANGE_ARGB = -0xa80e9
//    private val COLOR_LIGHT_ORANGE_ARGB = -0x657db
//    private val POLYGON_STROKE_WIDTH_PX = 4
//    private val PATTERN_DASH_LENGTH_PX = 20
//    private val PATTERN_GAP_LENGTH_PX = 20
//    private val COLOR_BLACK_ARGB = -0x1000000
//    private val DASH: PatternItem = Dash(PATTERN_DASH_LENGTH_PX.toFloat())
//    private val DOT: PatternItem = Dot()
//    private val GAP: PatternItem = Gap(PATTERN_GAP_LENGTH_PX.toFloat())
//
//    // Create a stroke pattern of a gap followed by a dash.
//    private val PATTERN_POLYGON_ALPHA = listOf(GAP, DASH)
//
//    // Create a stroke pattern of a dot followed by a gap, a dash, and another gap.
//    private val PATTERN_POLYGON_BETA = listOf(DOT, GAP, DASH, GAP)
//
//    /**
//     * Styles the polygon, based on type.
//     * @param polygon The polygon object that needs styling.
//     */
//    private fun stylePolygon(polygon: Polygon) {
//        // Get the data object stored with the polygon.
//        val type = polygon.tag?.toString() ?: ""
//        var pattern: List<PatternItem>? = null
//        var strokeColor = COLOR_BLACK_ARGB
//        var fillColor = COLOR_WHITE_ARGB
//        when (type) {
//            "alpha" -> {
//                // Apply a stroke pattern to render a dashed line, and define colors.
//                pattern = PATTERN_POLYGON_ALPHA
//                strokeColor = COLOR_DARK_GREEN_ARGB
//                fillColor = COLOR_LIGHT_GREEN_ARGB
//            }
//            "beta" -> {
//                // Apply a stroke pattern to render a line of dots and dashes, and define colors.
//                pattern = PATTERN_POLYGON_BETA
//                strokeColor = COLOR_DARK_ORANGE_ARGB
//                fillColor = COLOR_LIGHT_ORANGE_ARGB
//            }
//        }
//        polygon.strokePattern = pattern
//        polygon.strokeWidth = POLYGON_STROKE_WIDTH_PX.toFloat()
//        polygon.strokeColor = strokeColor
////        polygon.fillColor = fillColor
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun setUpMap() {
//        mMap.isMyLocationEnabled = true
//        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
//
//            if (location != null) {
//                lastLocation = location
//                val currentLatLong = LatLng(location.latitude, location.longitude)
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, 5f))
//            }
//        }
//
//        addMarkers(mMap)
//        mMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))
//    }
//
//    private val places: List<Place> by lazy {
//        PlacesReader(requireContext()).read()
//    }
//
//    private val bicycleIcon: BitmapDescriptor by lazy {
//        val color =
//            ContextCompat.getColor(requireContext(), androidx.appcompat.R.color.primary_dark_material_dark)
//        BitmapHelper.vectorToBitmap(requireContext(), R.drawable.ic_directions_bike_black_24dp, color)
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    private fun addMarkers(googleMap: GoogleMap) {
////        // Create a LatLngBounds that includes the city of Adelaide in Australia.
////        val adelaideBounds = LatLngBounds(
////            LatLng(-35.0, 138.58),  // SW bounds
////            LatLng(-34.9, 138.61) // NE bounds
////        )
////        // Constrain the camera target to the Adelaide bounds.
////        googleMap.setLatLngBoundsForCameraTarget(adelaideBounds)
//
//        places.forEach { place ->
//            if (place.cat == "food") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("Marker in Sydney")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_burger))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "block") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_building))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "block2") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_building2))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "hall") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_stage))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "print") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_print))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "hangout") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_park))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "med") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_medicine))
//                )
//                marker?.tag = place
//            }
//            if (place.cat == "sport") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_stadium))
//                )
//                marker?.tag = place
//            }
//
//            if (place.cat == "store") {
//                val marker = googleMap.addMarker(
//                    MarkerOptions().position(place.latLng).title("LC block")
//                        .icon(bitmapDescriptorFromVector(requireContext(), R.drawable.ic_store))
//                )
//                marker?.tag = place
//            }
//
////            val marker = googleMap.addMarker(
////                MarkerOptions().position(place.latLng).title("LC block")
////                    .icon(bitmapDescriptorFromVector(this, R.drawable.books))
////            )
////            marker?.tag = place
//
////            val marker = googleMap.addMarker(
////                MarkerOptions()
////                    .title(place.name)
////                    .position(place.latLng)
////                    .icon(bicycleIcon)
////            )
//
//            // Set place as the tag on the marker object so it can be referenced within
//            // MarkerInfoWindowAdapter
////            marker?.tag = place
//        }
//    }
//
//    private fun bitmapDescriptorFromVector(
//        context: Context,
//        vectorResId: Int
//    ): BitmapDescriptor {
//        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
//        vectorDrawable!!.setBounds(
//            0,
//            0,
//            vectorDrawable.getIntrinsicWidth(),
//            vectorDrawable.getIntrinsicHeight()
//        )
//        val bitmap = Bitmap.createBitmap(
//            vectorDrawable.getIntrinsicWidth(),
//            vectorDrawable.getIntrinsicHeight(),
//            Bitmap.Config.ARGB_8888
//        )
//        val canvas = Canvas(bitmap)
//        vectorDrawable.draw(canvas)
//        return BitmapDescriptorFactory.fromBitmap(bitmap)
//    }
//
//    override fun onMapReady(p0: GoogleMap) {
//        TODO("Not yet implemented")
//    }
}