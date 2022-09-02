package kr.hs.dgsw.smartschool.dodamdodam.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemApplySongBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat

class ApplySongAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<Video, ItemApplySongBinding>(
    R.layout.item_apply_song,
    SongDiffUtilCallback
) {
    override fun action(item: Video, binding: ItemApplySongBinding) {
        Glide.with(binding.ivSong)
            .load(item.thumbnail)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivSong)

        binding.tvApplySongDate.text = item.submitDate?.yearDateFormat()
        binding.song = item
        binding.root.setOnClickListener {
            action.invoke(item.videoUrl)
        }
    }
}
