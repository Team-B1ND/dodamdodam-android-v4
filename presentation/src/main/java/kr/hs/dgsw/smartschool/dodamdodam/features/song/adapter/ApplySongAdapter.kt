package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemApplySongBinding
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

class ApplySongAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<VideoSongData, ItemApplySongBinding>(
    R.layout.item_apply_song,
    SongDiffUtilCallback
) {
    override fun action(item: VideoSongData, binding: ItemApplySongBinding) {
        binding.song = item
        binding.root.setOnClickListener {
            action.invoke(item.videoUrl)
        }
    }
}
