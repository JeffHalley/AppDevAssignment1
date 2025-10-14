package ie.setu.appdevassignment1.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.appdevassignment1.R
import ie.setu.appdevassignment1.databinding.ActivityDeviceBinding
import ie.setu.appdevassignment1.main.MainApp
import ie.setu.appdevassignment1.models.DeviceModel
import timber.log.Timber
import timber.log.Timber.i

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceBinding
    var device = DeviceModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAdd)

        app = application as MainApp
        i("Iot Device Activity started...")

        binding.btnAdd.setOnClickListener() {
            device.title = binding.deviceTitle.text.toString()
            device.description = binding.deviceDescription.text.toString()

            if (device.title.isNotEmpty() && device.description.isNotEmpty()) {
                i("add Button Pressed: ${device.title}")
                app.devices.add(device.copy())
                for (i in app.devices.indices) {
                    i("[$i] ${app.devices[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar
                    .make(it, "Incomplete Data", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_device, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                // User pressed cancel
                finish() // just closes this activity and goes back
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}