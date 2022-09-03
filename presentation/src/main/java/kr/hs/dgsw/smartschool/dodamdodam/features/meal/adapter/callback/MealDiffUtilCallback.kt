package kr.hs.dgsw.smartschool.dodamdodam.features.meal.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo

object MealDiffUtilCallback : DiffUtil.ItemCallback<MealInfo>() {
    override fun areItemsTheSame(oldItem: MealInfo, newItem: MealInfo) = oldItem == newItem
    override fun areContentsTheSame(oldItem: MealInfo, newItem: MealInfo) = oldItem == newItem
}
