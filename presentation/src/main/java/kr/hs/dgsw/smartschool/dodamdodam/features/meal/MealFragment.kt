package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentMealBinding
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import java.time.LocalDate
import java.util.*

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {
    override val viewModel: MealViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var mealList = listOf<Meal>()

    override fun observerViewModel() {
        bindingViewEvent()
        with(viewModel) {
            targetDate.observe(this@MealFragment) {
                mBinding.tvDate.text = it.toString()
            }

            lifecycleScope.launchWhenStarted {
                mealState.collect { state ->
                    if (mealState.value.meal.isNotEmpty()) {
                        mealList = mealState.value.meal
                        getMeal(mealList)
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

    private fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@MealFragment) { it ->
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        MealViewModel.EVENT_CLICK_DATE -> {
                            targetDate.value?.also {
                                val year = it.year
                                val month = it.monthValue
                                val day = it.dayOfMonth

                                DatePickerDialog(requireContext(), { _, y, m, d ->
                                    val cal = Calendar.getInstance()
                                    cal.set(y, m + 1, d)
                                    setTargetDate(LocalDate.of(y, m + 1, d))
                                    if (month != m + 1) {
                                        getMealList()
                                        return@DatePickerDialog
                                    }
                                    getMeal(mealList)
                                }, year, month - 1, day).show()
                            }
                        }
                        MealViewModel.EVENT_UPDATE_DATE -> {
                            getMeal(mealList)
                        }
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