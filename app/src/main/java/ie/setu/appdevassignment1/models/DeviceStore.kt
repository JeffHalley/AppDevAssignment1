package ie.setu.appdevassignment1.models;


interface DeviceStore {
    fun findAll(): List<DeviceModel>
    fun create(device: DeviceModel)
}
