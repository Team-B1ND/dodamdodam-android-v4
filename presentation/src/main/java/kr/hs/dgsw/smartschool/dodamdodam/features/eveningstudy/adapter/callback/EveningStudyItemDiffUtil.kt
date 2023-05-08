package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem

object EveningStudyItemDiffUtil : DiffUtil.ItemCallback<EveningStudyItem>() {
    override fun areContentsTheSame(oldItem: EveningStudyItem, newItem: EveningStudyItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: EveningStudyItem, newItem: EveningStudyItem): Boolean {
        return oldItem == newItem
    }
}
