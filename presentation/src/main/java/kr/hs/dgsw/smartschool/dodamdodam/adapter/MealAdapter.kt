package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.MealDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemMealBinding
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo

class MealAdapter : BaseListAdapter<MealInfo, ItemMealBinding>(
    R.layout.item_meal,
    MealDiffUtilCallback
) {

    override fun action(data: MealInfo, binding: ItemMealBinding) {
        val image = when (data.type) {
            1 -> R.drawable.ic_breakfast
            2 -> R.drawable.ic_lunch
            3 -> R.drawable.ic_dinner
            else -> return
        }

        Glide.with(binding.root)
            .load(image)
            .into(binding.ivMeal)

        val context = binding.root.context

        when (data.type) {
            1 -> {
                binding.tvMealName.text = "조식"
                binding.tvMealName.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_breakfast))
            }
            2 -> {
                binding.tvMealName.text = "중식"
                binding.tvMealName.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_lunch))
            }
            3 -> {
                binding.tvMealName.text = "석식"
                binding.tvMealName.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.color_dinner))
            }
        }

        binding.tvMeal.text = data.content
    }
}
