package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.itmap.Company
import kr.hs.dgsw.smartschool.domain.model.itmap.ItMapUser

object ItMapUserDiffUtilCallback : DiffUtil.ItemCallback<ItMapUser>() {
    override fun areItemsTheSame(oldItem: ItMapUser, newItem: ItMapUser): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ItMapUser, newItem: ItMapUser): Boolean {
        return oldItem.id == newItem.id
    }

}