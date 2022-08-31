package com.example.nmamitmap

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import java.lang.ClassCastException

class GpsUtils{

    companion object {
        var tag = "locationLog"

        fun showLocationPrompt(context: Context): Boolean {
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

            var response: LocationSettingsResponse? = null

            var result: Task<LocationSettingsResponse> =
                LocationServices.getSettingsClient(context).checkLocationSettings(builder.build())
            result.addOnCompleteListener { task ->
                try {
                    response = task.getResult(ApiException::class.java)
                } catch (exception: ApiException) {
                    when (exception.statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            try {
                                val resolvable: ResolvableApiException =
                                    exception as ResolvableApiException
                                resolvable.startResolutionForResult(
                                    context as Activity,
                                    LocationRequest.PRIORITY_HIGH_ACCURACY
                                )
                            } catch (e: IntentSender.SendIntentException) {
                                Log.e(tag, "Exception IS $e")
                            } catch (e: ClassCastException) {
                                Log.e(tag, "Exception CCE $e")
                            }
                        }

                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            Log.e(tag, "unavailable")
                        }
                    }
                }
            }

            return true
        }
    }
}