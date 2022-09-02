package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSongBinding
import kr.hs.dgsw.smartschool.domain.model.song.VideoSongData

class SongAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<VideoSongData, ItemSongBinding>(
    R.layout.item_song,
    SongDiffUtilCallback
) {
    override fun action(item: VideoSongData, binding: ItemSongBinding) {
        Glide.with(binding.ivTodaySong)
            .load(item.thumbnailUrl)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivTodaySong)

        binding.song = item
        binding.root.setOnClickListener {
            action.invoke(item.videoUrl)
        }
    }
}
