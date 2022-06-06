package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentMealBinding
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {
    override val viewModel: MealViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
        with(viewModel) {
            targetDate.observe(this@MealFragment) {
                mBinding.tvDate.text = it.toString()
                Toast.makeText(requireContext(), targetDate.toString(), Toast.LENGTH_SHORT).show()
            }

            lifecycleScope.launchWhenStarted {
                mealState.collect { state ->
                    if (mealState.value.meal.isNotEmpty()) {
                        getMeal(mealState.value.meal)
                    }
                    if (state.isLoading) {
                        Toast.makeText(requireContext(), "로딩중학교", Toast.LENGTH_SHORT).show()
                    }
                    if (state.error.isNotBlank()) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun getMeal(mealList: List<Meal>) {
        val meal = mealList.find { meal ->
            meal.date == viewModel.targetDate.value.toString()
        }
        meal?.let {
            setMealRecycler(it)
        }
    } // getMeal()

    private fun setMealRecycler(meal: Meal) {
        val mealAdapter = MealAdapter()
        mBinding.recyclerMeal.adapter = mealAdapter
        mealAdapter.submitList(
            listOf(
                MealInfo(1, meal.safeBreakfast),
                MealInfo(2, meal.safeLunch),
                MealInfo(3, meal.safeDinner)
            )
        )
    } // setMealRecycler
}