package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem

object NightStudyItemDiffUtil : DiffUtil.ItemCallback<NightStudyItem>() {
    override fun areContentsTheSame(oldItem: NightStudyItem, newItem: NightStudyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: NightStudyItem, newItem: NightStudyItem): Boolean {
        return oldItem == newItem
    }
}
