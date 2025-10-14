package ie.setu.appdevassignment1.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.setu.appdevassignment1.R
import ie.setu.appdevassignment1.databinding.ActivityDeviceListBinding
import ie.setu.appdevassignment1.databinding.CardDeviceBinding
import ie.setu.appdevassignment1.main.MainApp
import ie.setu.appdevassignment1.models.DeviceModel

class DeviceListActivity : AppCompatActivity() {

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
        binding.recyclerView.adapter = DeviceAdapter(app.devices)
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

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.devices.size)
            }
        }

}


class DeviceAdapter constructor(private var devices: List<DeviceModel>) :
    RecyclerView.Adapter<DeviceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDeviceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val device = devices[holder.adapterPosition]
        holder.bind(device)
    }

    override fun getItemCount(): Int = devices.size

    class MainHolder(private val binding: CardDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: DeviceModel) {
            binding.deviceTitle.text = device.title
            binding.deviceDescription.text = device.description
        }
    }
}
