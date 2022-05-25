package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()

    override fun observerViewModel() {
        setUpTodayMeal()
    }

    private fun setUpTodayMeal() {
        val mealHomeAdapter = MealHomeAdapter()
        mBinding.viewPagerMealList.adapter = mealHomeAdapter
        mBinding.viewPagerMealList.offscreenPageLimit = 3
        mBinding.viewPagerMealList.setPadding(90, 0, 90, 0)

        var transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(10))

        transform.addTransformer { view: View, fl: Float ->
            var v = 1 - Math.abs(fl)
            view.scaleY = 0.8f + v * 0.2f
        }
        mBinding.viewPagerMealList.setPageTransformer(transform)
        mealHomeAdapter.submitList(
            listOf(
                MealInfo(1, "쇠고기버섯죽 , *크로크무슈 , 나박물김치 , *오레오오즈레드+우유 , 바나나"),
                MealInfo(2, "*발아현미밥 , *놀부부대찌개 , *꽁치감자조림 , *시카고피자 , 배추김치 , 납작복숭아주스"),
                MealInfo(3, "*기장밥 , 김치어묵국 , *명태껍질볶음 , 새송이돈육마늘구이 , *짜먹는요거트 , *꽃상추쌈/쌈장")
            )
        )
    }
}