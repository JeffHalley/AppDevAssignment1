package ie.setu.appdevassignment1.models


class DeviceMemStore : DeviceStore {

    val devices = ArrayList<DeviceModel>()

    override fun findAll(): List<DeviceModel> {
        return devices
    }

    override fun create(device: DeviceModel) {
        devices.add(device)
    }
}