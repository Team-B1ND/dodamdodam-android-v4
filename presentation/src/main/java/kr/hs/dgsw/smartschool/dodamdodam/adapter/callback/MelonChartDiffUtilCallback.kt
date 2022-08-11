package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart

object MelonChartDiffUtilCallback : DiffUtil.ItemCallback<MelonChart>() {
    override fun areItemsTheSame(oldItem: MelonChart, newItem: MelonChart) = oldItem == newItem
    override fun areContentsTheSame(oldItem: MelonChart, newItem: MelonChart) = oldItem == newItem
}