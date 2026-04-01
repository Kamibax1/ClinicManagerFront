package com.example.clinicmanagerfront

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClinicManagerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e("TravelAgencyApp", "========================================")
        Log.e("TravelAgencyApp", "APPLICATION STARTED!")
        Log.e("TravelAgencyApp", "========================================")
    }
}