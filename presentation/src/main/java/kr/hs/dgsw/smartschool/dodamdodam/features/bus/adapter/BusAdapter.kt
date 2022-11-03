package kr.hs.dgsw.smartschool.dodamdodam.features.bus.adapter

import android.content.Context
import android.view.View
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.bus.adapter.callback.BusDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

class BusAdapter(val context: Context, val listener: BusApplyCallBack) : BaseListAdapter<BusInfo, ItemBusBinding>(R.layout.item_bus, BusDiffUtilCallback) {

    override fun action(item: BusInfo, binding: ItemBusBinding) {
        if (item.isSelected) {
            binding.tvBusRidePossible.visibility = View.INVISIBLE
            binding.menu.visibility = View.VISIBLE
        }
        else {
            binding.tvBusRidePossible.visibility = View.VISIBLE
            binding.menu.visibility = View.INVISIBLE
        }

        with(binding) {
            tvBusRidePossible.setImageResource(
                if (item.rideable) R.drawable.ic_circle_blue_light
                else R.drawable.ic_circle_yellow_light
            )
            bus = item

            root.setOnClickListener {
                listener.applyBus(item.idx)
            }
        }
    }

    interface BusApplyCallBack {
        fun applyBus(idx: Int)
    }
}
