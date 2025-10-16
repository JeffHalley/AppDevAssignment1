package ie.setu.appdevassignment1.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.setu.appdevassignment1.R
import ie.setu.appdevassignment1.adapters.DeviceAdapter
import ie.setu.appdevassignment1.adapters.DeviceListener
import ie.setu.appdevassignment1.databinding.ActivityDeviceListBinding
import ie.setu.appdevassignment1.main.MainApp
import ie.setu.appdevassignment1.models.DeviceModel

class DeviceListActivity : AppCompatActivity(), DeviceListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityDeviceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = DeviceAdapter(app.devices.findAll(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, DeviceActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDeviceDelete(device: DeviceModel) {
        val position = app.devices.findAll().indexOf(device)
        if (position != -1) {
            app.devices.delete(device)
            binding.recyclerView.adapter?.notifyItemRemoved(position)
        }
        }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.devices.findAll().size)
            }
        }

    override fun onDeviceClick(device: DeviceModel) {
        val launcherIntent = Intent(this, DeviceActivity::class.java)
        launcherIntent.putExtra("device_edit", device)
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.notifyItemRangeChanged(0, app.devices.findAll().size)
            }
        }
}
