package ie.setu.appdevassignment1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.setu.appdevassignment1.databinding.CardDeviceBinding
import ie.setu.appdevassignment1.models.DeviceModel

interface DeviceListener {
    fun onDeviceClick(device: DeviceModel)
    fun onDeviceDelete(device: DeviceModel)
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
            // Set the device title
            binding.deviceTitle.text = device.title

            // Set the activated date with default
            binding.deviceActivatedAt.text = device.activatedAt.ifEmpty {
                "Not Date available"
            }

            // Set the sensor family with default
            binding.deviceSensorFamily.text = device.sensorFamily.ifEmpty {
                "no family available"
            }


            val colorRes =
                if (device.status) android.R.color.holo_green_light else android.R.color.holo_red_light
            binding.statusIndicator.background.setTint(
                binding.root.context.resources.getColor(
                    colorRes,
                    null
                )
            )

            // Edit button click
            binding.btnEditCard.setOnClickListener {
                listener.onDeviceClick(device)
            }

            // Delete button click
            binding.btnDeleteCard.setOnClickListener {
                listener.onDeviceDelete(device)
            }
        }
    }
}
