package ie.setu.appdevassignment1.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class DeviceMemStore : DeviceStore {

    val devices = ArrayList<DeviceModel>()

    override fun findAll(): List<DeviceModel> {
        return devices
    }

    override fun create(device: DeviceModel) {
        device.id = getId()
        devices.add(device)
        logAll()
    }

    override fun update(device: DeviceModel) {
        var foundDevice: DeviceModel? = devices.find { d -> d.id == device.id }
        if (foundDevice != null) {
            foundDevice.title = device.title
            foundDevice.description = device.description
            logAll()
        }
    }

    private fun logAll() {
        devices.forEach { i("$it") }
    }
}
