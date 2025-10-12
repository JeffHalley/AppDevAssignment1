package ie.setu.appdevassignment1.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ie.setu.appdevassignment1.databinding.ActivityDeviceBinding
import ie.setu.appdevassignment1.models.DeviceModel
import timber.log.Timber
import timber.log.Timber.i

class DeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceBinding
    var device = DeviceModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

        i("Iot Device Activity started...")

        binding.btnAdd.setOnClickListener() {
            device.title = binding.deviceTitle.text.toString()
            device.description = binding.deviceDescription.text.toString()

            if (device.title.isNotEmpty() && device.description.isNotEmpty()) {
                i("add Button Pressed: ${device.title}")
                i("Add description button pressed: ${device.description} ")
            } else {
                Snackbar
                    .make(it, "Incomplete Data", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}