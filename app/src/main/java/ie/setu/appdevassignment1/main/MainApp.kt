package ie.setu.appdevassignment1.main

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ie.setu.appdevassignment1.models.DeviceMemStore
import ie.setu.appdevassignment1.models.DeviceModel
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var devices: DeviceMemStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Device app started")

        devices = DeviceMemStore(this)
        devices.load()
    }
}