package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil

object StudyRoomDiffUtilCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Location, newItem: Location) = oldItem == newItem
}
