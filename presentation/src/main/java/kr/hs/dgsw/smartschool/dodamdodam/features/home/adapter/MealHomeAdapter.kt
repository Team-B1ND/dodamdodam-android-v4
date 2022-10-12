package kr.hs.dgsw.smartschool.dodamdodam.features.home.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemMealHomeBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.adapter.callback.MealDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import java.time.LocalDateTime

class MealHomeAdapter(val onClickCard: () -> Unit) : BaseListAdapter<MealInfo, ItemMealHomeBinding>(
    R.layout.item_meal_home,
    MealDiffUtilCallback
) {
    override fun action(item: MealInfo, binding: ItemMealHomeBinding) {
        val image = when (item.type) {
            1 -> R.drawable.ic_breakfast
            2 -> R.drawable.ic_lunch
            3 -> R.drawable.ic_dinner
            else -> return
        }

        Glide.with(binding.root)
            .load(image)
            .into(binding.ivMeal)

        if (LocalDateTime.now().hour >= 20) {
            setMealType(binding, item.type, "내일의")
        } else {
            setMealType(binding, item.type, "오늘의")
        }

        binding.tvMeal.text = item.content

        binding.cardThumbnail.setOnClickListener {
            onClickCard.invoke()
        }
    }

    private fun setMealType(binding: ItemMealHomeBinding, type: Int, dayValue: String) {
        when (type) {
            1 -> {
                binding.tvMealType.text = "$dayValue 조식"
            }
            2 -> {
                binding.tvMealType.text = "$dayValue 중식"
            }
            3 -> {
                binding.tvMealType.text = "$dayValue 석식"
            }
        }
    }
}
