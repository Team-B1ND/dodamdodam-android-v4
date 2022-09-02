package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemApplySongBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

class ApplySongAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<VideoSongData, ItemApplySongBinding>(
    R.layout.item_apply_song,
    SongDiffUtilCallback
) {
    override fun action(item: VideoSongData, binding: ItemApplySongBinding) {
        Glide.with(binding.ivSong)
            .load(item.thumbnailUrl)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivSong)

        binding.tvApplySongDate.text = item.createdDate.yearDateFormat()
        binding.song = item
        binding.root.setOnClickListener {
            action.invoke(item.videoUrl)
        }
    }
}
