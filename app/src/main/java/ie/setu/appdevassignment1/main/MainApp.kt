package ie.setu.appdevassignment1.main

import android.app.Application
import ie.setu.appdevassignment1.models.DeviceModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val devices = ArrayList<DeviceModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Device app started")

        devices.add(DeviceModel("Temperature Sensor", "Monitors ambient temperature in real time"))
        devices.add(DeviceModel("pH Sensor", "Measures water acidity for environmental monitoring"))
        devices.add(DeviceModel("Ultrasonic Sensor", "Used for distance and obstacle detection"))
        devices.add(DeviceModel("Smart Light", "IoT-controlled LED light with adjustable brightness"))
        devices.add(DeviceModel("Motion Detector", "Triggers events based on movement detection"))
    }

}