package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

class BusAdapter(busList: List<BusByDate>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val data = busList
    class BusViewHolder(val binding: ItemBusBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusByDate){
            binding.bus = data.bus
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BusAdapter.BusViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_bus,
                parent,
                false
            ),
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BusAdapter.BusViewHolder).apply {
            bind(data.get(position))
        }
    }
}