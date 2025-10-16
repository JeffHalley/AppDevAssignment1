package ie.setu.appdevassignment1.models;


interface DeviceStore {
    fun update(device: DeviceModel)
    fun findAll(): List<DeviceModel>
    fun create(device: DeviceModel)
}
