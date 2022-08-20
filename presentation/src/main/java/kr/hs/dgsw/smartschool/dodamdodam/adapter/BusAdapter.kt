package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.BusDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBusBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo


class BusAdapter(val context: Context, private val callback: BusAdapter.BusApplyCallBack) : ListAdapter<BusInfo, BusAdapter.BusViewHolder>(BusDiffUtilCallback) {

    interface BusApplyCallBack {
        fun applyBus(idx: Int)
        fun cancelBus(idx: Int)
    }

    inner class BusViewHolder(private val binding: ItemBusBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusInfo){

            //TODO 버스 카드 선택에 따른 background color 설정 필요, BusInfo 구조 변경 필요
            with(binding.tvBusRidePossible) {
                background = if (data.rideable == "탑승가능")
                    ContextCompat.getDrawable(context, R.drawable.background_bus_ride_possible)
                else
                    ContextCompat.getDrawable(context, R.drawable.background_bus_ride_impossible)
            }

            binding.bus = data

            binding.menu.setOnClickListener(View.OnClickListener {
                val pm : PopupMenu = PopupMenu(context,binding.menu)
                pm.inflate(R.menu.bus_item_menu)

                pm.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.apply_bus ->

                            true
                        R.id.cancel_bus ->
                            true
                        else -> false
                    }
                })
                pm.show()

            })
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