package com.example.watchlistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WatchlistApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialisation globale si nécessaire
    }
}
