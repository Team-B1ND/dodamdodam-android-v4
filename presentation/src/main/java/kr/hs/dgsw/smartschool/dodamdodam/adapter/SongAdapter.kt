package kr.hs.dgsw.smartschool.dodamdodam.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSongBinding
import kr.hs.dgsw.smartschool.domain.model.song.Video

class SongAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<Video, ItemSongBinding>(
    R.layout.item_song,
    SongDiffUtilCallback
) {
    override fun action(item: Video, binding: ItemSongBinding) {
        Glide.with(binding.ivTodaySong)
            .load(item.thumbnail)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivTodaySong)

        binding.song = item
        binding.ivTodaySong.setOnClickListener {
            action.invoke(item.videoUrl)
        }
    }
}
