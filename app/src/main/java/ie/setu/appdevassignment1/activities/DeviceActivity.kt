package ie.setu.appdevassignment1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.setu.appdevassignment1.R
import ie.setu.appdevassignment1.databinding.ActivityDeviceBinding
import ie.setu.appdevassignment1.main.MainApp
import ie.setu.appdevassignment1.models.DeviceModel
import timber.log.Timber.i

class DeviceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeviceBinding
    var device = DeviceModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("device_edit")) {
            device = intent.extras?.getParcelable("device_edit")!!
            binding.deviceTitle.setText(device.title)
            binding.deviceDescription.setText(device.description)
        }

        binding.btnAdd.setOnClickListener {
            device.title = binding.deviceTitle.text.toString()
            device.description = binding.deviceDescription.text.toString()
            if (device.title.isNotEmpty()) {
                app.devices.add(device.copy())
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar.make(it, "Please enter a title", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_device, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
