package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

object LostFoundDiffUtilCallback : DiffUtil.ItemCallback<LostInfo>() {
    override fun areItemsTheSame(oldItem: LostInfo, newItem: LostInfo): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: LostInfo, newItem: LostInfo): Boolean = oldItem == newItem
}
