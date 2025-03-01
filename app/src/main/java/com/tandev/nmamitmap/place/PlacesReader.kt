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

package com.example.nmamitmap

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tandev.nmamitmap.MapsActivity
import java.io.InputStream
import java.io.InputStreamReader

class PlacesReader(private val context: Context) {

    private val gson = Gson()

    private val body = MapsActivity.RES_BODY2

    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.places)

    fun read(): List<Place> {
        val itemType = object : TypeToken<List<PlaceResponse>>() {}.type

        val reader1: String = body

//        Log.d("reader2", (reader1=="").toString())

        if (MapsActivity.CAN_FETCH && reader1.isNotEmpty()) {
            Log.d("reader", reader1 + reader1.isEmpty().toString())
            return gson.fromJson<List<PlaceResponse>>(reader1, itemType).map {
                it.toPlace()
            }
        } else {
            val reader2 = InputStreamReader(inputStream)
            return gson.fromJson<List<PlaceResponse>>(reader2, itemType).map {
                it.toPlace()
            }
        }
    }
}