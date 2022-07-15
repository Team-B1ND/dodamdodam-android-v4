package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.BusDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

class BusAdapter : ListAdapter<BusInfo, BusAdapter.BusViewHolder>(BusDiffUtilCallback) {

    class BusViewHolder(val binding: ItemBusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusInfo){
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