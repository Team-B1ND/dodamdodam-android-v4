package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemRecommendSongBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.MelonChartDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.song.melon.SongChart

class RecommendSongAdapter(val action: (title: String) -> Unit) : BaseListAdapter<SongChart, ItemRecommendSongBinding>(
    R.layout.item_recommend_song,
    MelonChartDiffUtilCallback
) {
    override fun action(item: SongChart, binding: ItemRecommendSongBinding) {

        binding.melon = item

        binding.root.setOnClickListener {
            action.invoke(item.name)
        }
    }
}
