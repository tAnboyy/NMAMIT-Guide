package com.example.nmamitmap.teacher

import com.google.android.gms.maps.model.LatLng

data class Teacher(
    val name: String,
    val branch: String,
    val latLng: LatLng,
    val vicinity: String,
    val phone: String,
    val index: Int
)
