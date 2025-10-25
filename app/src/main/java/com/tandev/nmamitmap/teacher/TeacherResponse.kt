// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.tandev.nmamitmap.teacher

import com.tandev.nmamitmap.teacher.Teacher
import com.google.android.gms.maps.model.LatLng
import com.tandev.nmamitmap.PlaceResponse

data class TeacherResponse(
    val geometry: PlaceResponse.Geometry,
    val name: String,
    val branch: String,
    val latLng: LatLng,
    val block: String,
    val floor: String,
    val phone: String,
    val index: Int,
    val imgUrl: String
) {

    data class Geometry(
        val location: GeometryLocation
    )

    data class GeometryLocation(
        val lat: Double,
        val lng: Double
    )
}

fun TeacherResponse.toTeacher(): Teacher = Teacher(
    name = name,
    latLng = LatLng(geometry.location.lat, geometry.location.lng),
    floor = floor,
    block = block,
    branch = branch,
    phone = phone,
    index = index,
    imgUrl = imgUrl
)
