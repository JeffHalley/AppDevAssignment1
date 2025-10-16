package ie.setu.appdevassignment1.main

import android.app.Application
import ie.setu.appdevassignment1.models.DeviceMemStore
import ie.setu.appdevassignment1.models.DeviceModel
import org.json.JSONArray
import timber.log.Timber
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
            // Read the JSON file from assets
            val jsonString = assets.open("devices.json")
                .bufferedReader()
                .use { it.readText() }

            // Parse JSON array
            val jsonArray = JSONArray(jsonString)

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val title = jsonObject.getString("title")
                val description = jsonObject.getString("description")
                devices.create(DeviceModel(title, description))
            }

            i("Loaded ${devices.findAll().size} devices from JSON")
        } catch (ex: Exception) {
            i("Error loading devices from JSON: ${ex.message}")
        }
    }

}