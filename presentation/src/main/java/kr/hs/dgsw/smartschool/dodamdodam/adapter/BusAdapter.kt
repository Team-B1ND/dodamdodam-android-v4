package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.BusDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

class BusAdapter(val context: Context) : ListAdapter<BusInfo, BusAdapter.BusViewHolder>(BusDiffUtilCallback) {

    inner class BusViewHolder(private val binding: ItemBusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusInfo) {
            with(binding.tvBusRidePossible) {
                background = if (data.rideable == "탑승가능")
                    ContextCompat.getDrawable(context, R.drawable.background_bus_ride_possible)
                else
                    ContextCompat.getDrawable(context, R.drawable.background_bus_ride_impossible)
            }

            binding.bus = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        return BusViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_bus,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}
