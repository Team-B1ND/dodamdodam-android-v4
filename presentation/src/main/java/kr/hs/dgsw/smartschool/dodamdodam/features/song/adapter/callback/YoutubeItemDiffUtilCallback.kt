package kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.song.youtube.Item

object YoutubeItemDiffUtilCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
}
