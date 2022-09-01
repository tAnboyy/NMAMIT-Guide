package com.tandev.nmamitmap
import com.google.android.gms.maps.model.LatLng
import kotlin.Comparable

data class MyComparableTeacher(val name: String,
                               val branch: String,
                               val latLng: LatLng,
                               val block: String,
                               val floor: String,
                               val phone: String,
                               val index: Int,
                               val imgUrl: String) : Comparable<MyComparableTeacher> {
    override fun compareTo(other: MyComparableTeacher) = when {
        name != other.name -> name.compareTo(other.name)
        else -> {
            branch.compareTo(other.branch)
        }
    }
}