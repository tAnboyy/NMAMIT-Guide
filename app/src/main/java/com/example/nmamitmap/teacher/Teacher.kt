package com.example.nmamitmap.teacher

import com.google.android.gms.maps.model.LatLng

data class Teacher(
    val name: String,
    val branch: String,
    val latLng: LatLng,
    val block: String,
    val floor: String,
    val phone: String,
    val index: Int,
    val imgUrl: String
)
//    : Comparable<Any> {
//    override fun compareTo(other: Any): Int {
//        TODO("Not yet implemented")
//    }
//}
