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
        i("Device started")
    }
}