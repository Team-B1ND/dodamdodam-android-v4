package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.place.Place

object PlaceDiffUtilCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Place, newItem: Place) = oldItem == newItem
}
