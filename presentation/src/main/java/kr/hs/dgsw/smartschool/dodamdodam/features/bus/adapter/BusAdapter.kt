package kr.hs.dgsw.smartschool.dodamdodam.features.bus.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.bus.adapter.callback.BusDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

class BusAdapter(val context: Context, val listener: BusApplyCallBack) : BaseListAdapter<BusInfo, ItemBusBinding>(R.layout.item_bus, BusDiffUtilCallback) {

    override fun action(item: BusInfo, binding: ItemBusBinding) {
        if (item.isSelected) {
            binding.busCard.background = ContextCompat.getDrawable(context, R.drawable.background_bus_card_selected)
            Log.e("background", item.isSelected.toString())
        }

        with(binding.tvBusRidePossible) {
            background = if (item.rideable == "탑승가능")
                ContextCompat.getDrawable(context, R.drawable.background_bus_ride_possible)
            else
                ContextCompat.getDrawable(context, R.drawable.background_bus_ride_impossible)
        }

        binding.bus = item

        binding.menu.setOnClickListener(
            View.OnClickListener {
                val pm: PopupMenu = PopupMenu(context, binding.menu)
                pm.inflate(R.menu.bus_item_menu)

                pm.setOnMenuItemClickListener(
                    PopupMenu.OnMenuItemClickListener { data ->
                        when (data.itemId) {
                            R.id.apply_bus ->
                                {
                                    listener.applyBus(item.idx)
                                    true
                                }
                            R.id.cancel_bus ->
                                {
                                    listener.cancelBus(item.idx)
                                    true
                                }
                            else -> false
                        }
                    }
                )
                pm.show()
            }
        )
    }

    interface BusApplyCallBack {
        fun applyBus(idx: Int)
        fun cancelBus(idx: Int)
    }
}
