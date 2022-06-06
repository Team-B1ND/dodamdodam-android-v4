package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentMealBinding
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {
    override val viewModel: MealViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
        setUpTodayMeal()
    }

    private fun setUpTodayMeal() {
        val mealAdapter = MealAdapter()
        mBinding.recyclerMeal.adapter = mealAdapter
        mealAdapter.submitList(
            listOf(
                MealInfo(1, "쇠고기버섯죽 , *크로크무슈 , 나박물김치 , *오레오오즈레드+우유 , 바나나"),
                MealInfo(2, "*발아현미밥 , *놀부부대찌개 , *꽁치감자조림 , *시카고피자 , 배추김치 , 납작복숭아주스"),
                MealInfo(3, "*기장밥 , 김치어묵국 , *명태껍질볶음 , 새송이돈육마늘구이 , *짜먹는요거트 , *꽃상추쌈/쌈장")
            )
        )
    }

}