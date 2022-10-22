package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart

object MelonChartDiffUtilCallback : DiffUtil.ItemCallback<SongChart>() {
    override fun areItemsTheSame(oldItem: SongChart, newItem: SongChart) = oldItem == newItem
    override fun areContentsTheSame(oldItem: SongChart, newItem: SongChart) = oldItem == newItem
}
