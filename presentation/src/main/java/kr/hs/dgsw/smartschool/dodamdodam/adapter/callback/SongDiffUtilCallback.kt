package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.song.Song

object SongDiffUtilCallback : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem
}