package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import android.app.DatePickerDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentMealBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import java.time.LocalDate
import java.util.Calendar

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {
    override val viewModel: MealViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                MealViewModel.EVENT_CLICK_DATE -> showDateDialog()
            }
        }
        mBinding.tvCalorieDate.text = LocalDate.now().toString()
        viewModel.targetDate.observe(this@MealFragment) {
            mBinding.tvDate.text = it.toString()
        }

        collectMealState()
        collectMealCalorie()
    }

    private fun showDateDialog() {
        with(viewModel) {
            targetDate.value?.also {
                val year = it.year
                val month = it.monthValue
                val day = it.dayOfMonth

                val datePickerDialog =
                    DatePickerDialog(
                        requireContext(),
                        R.style.MyDatePickerDialogTheme,
                        { _, y, m, d ->
                            val cal = Calendar.getInstance()
                            cal.set(y, m + 1, d)
                            setTargetDate(LocalDate.of(y, m + 1, d))
                            getMeal()
                        },
                        year,
                        month - 1,
                        day
                    )
                datePickerDialog.show()
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.main))
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                    .setTextColor(ContextCompat.getColor(requireContext(), R.color.main))
            }
        }
    }

    private fun collectMealState() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMealState.collect { state ->
                    if (state.isUpdate) {
                        state.meal?.let {
                            setMealRecycler(it)
                        }
                    }

                    if (state.error.isNotBlank()) {
                        setMealRecycler(
                            meal = Meal(
                                "값을 받아올 수 없습니다.",
                                "",
                                "값을 받아올 수 없습니다.",
                                false,
                                "값을 받아올 수 없습니다."
                            )
                        )
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun collectMealCalorie() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMealCalorieState.collect { state ->
                    if (state.isUpdate) {
                        mBinding.tvCalorie.text = state.calorie ?: "급식이 없군요.."
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

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
    }
}
