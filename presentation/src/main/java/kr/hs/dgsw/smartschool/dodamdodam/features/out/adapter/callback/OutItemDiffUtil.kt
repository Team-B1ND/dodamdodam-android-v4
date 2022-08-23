package kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.out.OutItem

object OutItemDiffUtil : DiffUtil.ItemCallback<OutItem>() {

    override fun areItemsTheSame(oldItem: OutItem, newItem: OutItem): Boolean {
        return oldItem.idx == newItem.idx
    }

    override fun areContentsTheSame(oldItem: OutItem, newItem: OutItem): Boolean {
        return oldItem == newItem
    }

}