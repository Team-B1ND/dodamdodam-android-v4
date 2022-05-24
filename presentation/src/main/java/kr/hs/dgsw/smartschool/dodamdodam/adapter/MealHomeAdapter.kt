package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.MealDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemMealHomeBinding
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo

class MealHomeAdapter : ListAdapter<MealInfo, MealHomeAdapter.MealHomeViewHolder>(MealDiffUtilCallback) {

    class MealHomeViewHolder(val binding: ItemMealHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealInfo) {
            val image = when (item.type) {
                1 -> R.drawable.ic_breakfast
                2 -> R.drawable.ic_lunch
                3 -> R.drawable.ic_dinner
                else -> return
            }

            Glide.with(binding.root)
                .load(image)
                .into(binding.ivMeal)

            val context = binding.root.context

            when(item.type) {
                1 -> {
                    binding.tvMealName.text = "조식"
                    binding.tvMealName.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorBreakfast))
                }
                2 -> {
                    binding.tvMealName.text = "중식"
                    binding.tvMealName.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorLunch))
                }
                3 -> {
                    binding.tvMealName.text = "석식"
                    binding.tvMealName.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorDinner))
                }
            }

            binding.tvMeal.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHomeViewHolder {
        return MealHomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_meal_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MealHomeViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}