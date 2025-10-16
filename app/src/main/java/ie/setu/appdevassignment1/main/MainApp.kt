package ie.setu.appdevassignment1.main

import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ie.setu.appdevassignment1.models.DeviceMemStore
import ie.setu.appdevassignment1.models.DeviceModel
import timber.log.Timber.i

class MainApp : Application() {

    val devices = DeviceMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Device app started")

        loadDevicesFromJson()
    }

    private fun loadDevicesFromJson() {
        try {
            val jsonString = assets.open("devices.json")
                .bufferedReader()
                .use { it.readText() }

            val gson = Gson()
            val listType = object : TypeToken<List<DeviceModel>>() {}.type
            val deviceList: List<DeviceModel> = gson.fromJson(jsonString, listType)

            deviceList.forEach { devices.create(it) }

            i("Loaded ${devices.findAll().size} devices from JSON")
        } catch (ex: Exception) {
            i("Error loading devices from JSON: ${ex.message}")
        }
    }
}
