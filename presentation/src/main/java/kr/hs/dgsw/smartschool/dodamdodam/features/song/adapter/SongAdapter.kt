package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSongBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

class SongAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<VideoSongData, ItemSongBinding>(
    R.layout.item_song,
    SongDiffUtilCallback
) {
    override fun action(item: VideoSongData, binding: ItemSongBinding) {
        binding.song = item
        binding.ivTodaySong.setOnClickListener {
            action.invoke(item.videoUrl)
        }
    }
}
