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
import com.example.nmamitmap.databinding.ActivitySearchTabBinding
import com.tandev.nmamitmap.teacher.Teacher
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tandev.nmamitmap.MapsActivity
import java.io.InputStream
import java.io.InputStreamReader

class TeachersReader(private val context: Context) {

    private val gson = Gson()

    val body = MapsActivity.RES_BODY

    private lateinit var binding: ActivitySearchTabBinding

    private val inputStream: InputStream
        get() = context.resources.openRawResource(R.raw.teachers)

    fun read(): List<Teacher> {
        val itemType = object : TypeToken<List<TeacherResponse>>() {}.type

        if (MapsActivity.CAN_FETCH) {
            val reader: String = body
            return gson.fromJson<List<TeacherResponse>>(reader, itemType).map {
                it.toTeacher()
            }
        } else {
            val reader = InputStreamReader(inputStream)
            return gson.fromJson<List<TeacherResponse>>(reader, itemType).map {
                it.toTeacher()
            }
        }


    }
}