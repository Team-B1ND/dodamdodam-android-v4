package kr.hs.dgsw.smartschool.dodamdodam.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.MelonChartDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemRecommendSongBinding
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart

class RecommendSongAdapter : BaseListAdapter<MelonChart, ItemRecommendSongBinding>(
    R.layout.item_recommend_song,
    MelonChartDiffUtilCallback
) {
    override fun action(item: MelonChart, binding: ItemRecommendSongBinding) {
        Glide.with(binding.ivRecommendSong)
            .load(item.thumbnail)
            .centerCrop()
            .error(R.drawable.default_img)
            .into(binding.ivRecommendSong)

        binding.tvRecommendSongTitle.text = item.title
        binding.tvRank.text = item.rank + "위"
        binding.tvRecommendSongArtist.text = item.artist
    }
}
