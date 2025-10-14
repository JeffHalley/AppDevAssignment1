package ie.setu.appdevassignment1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = DeviceAdapter(app.devices)
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

    class MainHolder(private val binding : CardDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: DeviceModel) {
            binding.deviceTitle.text = device.title
            binding.deviceDescription.text = device.description
        }
    }
}
