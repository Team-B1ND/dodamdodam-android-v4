package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo

object BusDiffUtilCallback : DiffUtil.ItemCallback<BusInfo>() {
    override fun areItemsTheSame(oldItem: BusInfo, newItem: BusInfo): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusInfo, newItem: BusInfo): Boolean = oldItem == newItem
}
