package com.aks.shagra

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.aks.shagra.data.local.SharedPref
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, "ar")

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //not the best practice at all //todo
        SharedPref.AddMainContext(this)
    }
}
