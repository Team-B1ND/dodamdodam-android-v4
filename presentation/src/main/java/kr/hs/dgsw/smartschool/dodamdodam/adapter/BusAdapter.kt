package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.BusDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

class BusAdapter(val context: Context) : BaseListAdapter<BusInfo, ItemBusBinding>(
    R.layout.item_bus,
    BusDiffUtilCallback
) {
    override fun action(item: BusInfo, binding: ItemBusBinding) {
        with(binding.tvBusRidePossible) {
            background = if (item.rideable == "탑승가능")
                ContextCompat.getDrawable(context, R.drawable.background_bus_ride_possible)
            else
                ContextCompat.getDrawable(context, R.drawable.background_bus_ride_impossible)
        }

        binding.bus = item
    }
}