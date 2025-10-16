package ie.setu.appdevassignment1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.appdevassignment1.databinding.CardDeviceBinding
import ie.setu.appdevassignment1.models.DeviceModel

interface DeviceListener {
    fun onDeviceClick(device: DeviceModel)
}

class DeviceAdapter(
    private var devices: List<DeviceModel>,
    private val listener: DeviceListener
) : RecyclerView.Adapter<DeviceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardDeviceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val device = devices[holder.adapterPosition]
        holder.bind(device, listener)
    }

    override fun getItemCount(): Int = devices.size

    class MainHolder(private val binding: CardDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(device: DeviceModel, listener: DeviceListener) {
            binding.deviceTitle.text = device.title
            binding.deviceDescription.text = device.description

            // Handle click on the entire card
            binding.root.setOnClickListener { listener.onDeviceClick(device) }


        }
    }
}
