package ie.setu.appdevassignment1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.setu.appdevassignment1.R
import ie.setu.appdevassignment1.main.MainApp

class DeviceListActivity : AppCompatActivity() {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_list)
        app = application as MainApp
    }
}
