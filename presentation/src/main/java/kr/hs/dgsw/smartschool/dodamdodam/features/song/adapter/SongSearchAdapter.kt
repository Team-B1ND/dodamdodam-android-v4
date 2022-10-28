package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSongSearchBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback.YoutubeItemDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Item

class SongSearchAdapter : BaseListAdapter<Item, ItemSongSearchBinding>(
    R.layout.item_song_search,
    YoutubeItemDiffUtilCallback
) {
    override fun action(item: Item, binding: ItemSongSearchBinding) {
        binding.item = item
    }
}
