package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil

object SongDiffUtilCallback : DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem
}
