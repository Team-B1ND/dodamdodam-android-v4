package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

object SongDiffUtilCallback : DiffUtil.ItemCallback<VideoSongData>() {
    override fun areItemsTheSame(oldItem: VideoSongData, newItem: VideoSongData) = oldItem == newItem
    override fun areContentsTheSame(oldItem: VideoSongData, newItem: VideoSongData) = oldItem == newItem
}
