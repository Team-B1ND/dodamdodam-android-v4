package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import android.view.View
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemApplySongBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

class ApplySongAdapter(private val id: String, val action: Action) : BaseListAdapter<VideoSongData, ItemApplySongBinding>(
    R.layout.item_apply_song,
    SongDiffUtilCallback
) {
    override fun action(item: VideoSongData, binding: ItemApplySongBinding) {
        if (item.applyMember.id == id)
            binding.ivDelete.visibility = View.VISIBLE
        else
            binding.ivDelete.visibility = View.GONE

        binding.song = item

        binding.ivSong.setOnClickListener {
            action.onClickItem(item.videoUrl)
        }

        binding.ivDelete.setOnClickListener {
            action.onClickDelete(item.id)
        }
    }

    interface Action {

        fun onClickItem(url: String)

        fun onClickDelete(itemId: Int)
    }
}
