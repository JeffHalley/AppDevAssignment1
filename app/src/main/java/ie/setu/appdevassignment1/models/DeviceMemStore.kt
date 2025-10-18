package ie.setu.appdevassignment1.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber.i
import java.io.File

var lastId = 0L
internal fun getId(): Long = lastId++

class DeviceMemStore(private val context: Context) : DeviceStore {

    private val gson = Gson()
    private val jsonFile = File(context.filesDir, "devices.json")
    private val listType = object : TypeToken<ArrayList<DeviceModel>>() {}.type

    var devices = ArrayList<DeviceModel>()

    override fun findAll(): List<DeviceModel> = devices

    override fun create(device: DeviceModel) {
        device.id = getId()
        devices.add(device)
        save()
        logAll()
    }

    override fun update(device: DeviceModel) {
        val foundDevice = devices.find { it.id == device.id }
        if (foundDevice != null) {
            foundDevice.title = device.title
            foundDevice.description = device.description
            foundDevice.status = device.status
            foundDevice.activatedAt = device.activatedAt
            foundDevice.sensorFamily = device.sensorFamily
            save()
            logAll()
        }
    }

    override fun delete(device: DeviceModel) {
        devices.removeAll { it.id == device.id }
        save()
        logAll()
    }

    fun load() {
        if (jsonFile.exists()) {
            val jsonString = jsonFile.readText()
            devices = gson.fromJson(jsonString, listType)
            i("Loaded ${devices.size} devices from internal JSON")
        } else {
            i("No internal JSON file found, loading from assets")
            loadFromAssets()
        }
    }

    private fun loadFromAssets() {
        try {
            val assetJson =
                context.assets.open("devices.json").bufferedReader().use { it.readText() }
            devices = gson.fromJson(assetJson, listType)
            save() // Save it to internal storage for future edits
            i("Seeded ${devices.size} devices from assets")
        } catch (ex: Exception) {
            i("Error reading devices from assets: ${ex.message}")
        }
    }

    private fun save() {
        jsonFile.writeText(gson.toJson(devices))
        i("Saved ${devices.size} devices to ${jsonFile.absolutePath}")
    }

    private fun logAll() {
        devices.forEach { i("Device: $it") }
    }
}
