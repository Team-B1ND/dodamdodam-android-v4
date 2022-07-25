package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LocationCheckAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.TodaySongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import kr.hs.dgsw.smartschool.domain.model.song.Song
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var mealList = listOf<Meal>()
    private var date: LocalDate = LocalDate.now()


    override fun observerViewModel() {
        setUpTodaySong()
        collectMealState()
        collectDataSetUpDate()
        collectMyLocation()
        bindViews()
    }

    private fun collectDataSetUpDate() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                dataSetUpState.collect { state ->
                    if (state.result != null) {
                        getMyLocation()
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun collectMyLocation() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMyLocationState.collect { state ->
                    if (state.myLocations.isNotEmpty()) {
                        setUpStudyRoom(state.myLocations)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun collectMealState() {
        with(viewModel) {
            if(LocalDateTime.now().hour >= 19) {
                date = date.plusDays(1)
                mBinding.tvMealTitle.text = "내일의 급식"
            }
            mBinding.tvMealDate.text = String.format("%d.%d", date.monthValue, date.dayOfMonth)
            getMealList(date)

            lifecycleScope.launchWhenStarted {
                mealState.collect { state ->
                    if (mealState.value.meal.isNotEmpty()) {
                        mBinding.progressLoading.visibility = View.GONE
                        mBinding.viewPagerMealList.visibility = View.VISIBLE
                        mealList = mealState.value.meal
                        getMeal(mealList, date)
                        setMealCurrentPosition()
                    }
                    if (state.isLoading) {
                        mBinding.progressLoading.visibility = View.VISIBLE
                        mBinding.viewPagerMealList.visibility = View.GONE
                    }
                    if (state.error.isNotBlank()) {
                        mBinding.progressLoading.visibility = View.GONE
                        mBinding.viewPagerMealList.visibility = View.VISIBLE
                        setMealViewPager(
                            meal = Meal(
                                "값을 받아올 수 없습니다.",
                                "",
                                "값을 받아올 수 없습니다.",
                                false,
                                "값을 받아올 수 없습니다."
                            )
                        )
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun bindViews() {
        mBinding.tvSongMore.setOnClickListener {
            (activity as? MainActivity)?.moveHomeToSong()
        }
        mBinding.tvMealMore.setOnClickListener {
            (activity as? MainActivity)?.moveHomeToMeal()
        }
        mBinding.btnMenu.setOnClickListener {
            openDrawer()
        }
    }

    private fun getMeal(mealList: List<Meal>, date: LocalDate) {
        val meal = mealList.find { meal ->
            meal.date == date.toString()
        }
        meal?.let {
            setMealViewPager(it)
        }
    } // getMeal()

    private fun setMealViewPager(meal: Meal) {
        val mealHomeAdapter = MealHomeAdapter()
        mBinding.viewPagerMealList.adapter = mealHomeAdapter
        mBinding.viewPagerMealList.offscreenPageLimit = 3
        mBinding.viewPagerMealList.setPadding(90, 0, 90, 0)
        mBinding.viewPagerMealList.setPageTransformer(getTransform())

        mealHomeAdapter.submitList(
            listOf(
                MealInfo(1, meal.safeBreakfast),
                MealInfo(2, meal.safeLunch),
                MealInfo(3, meal.safeDinner)
            )
        )
    }

    private fun setMealCurrentPosition() {
        when(LocalDateTime.now().hour) {
            in 9..13 -> mBinding.viewPagerMealList.currentItem = 1
            in 14..20 -> mBinding.viewPagerMealList.currentItem = 2
            else -> mBinding.viewPagerMealList.currentItem = 0
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun openDrawer() {
        mBinding.layoutDrawer.openDrawer(Gravity.LEFT, true)
    }

    private fun setUpTodaySong() {
        val todaySongAdapter = TodaySongAdapter()
        mBinding.viewPagerTodaySong.adapter = todaySongAdapter
        mBinding.viewPagerTodaySong.offscreenPageLimit = 3
        mBinding.viewPagerTodaySong.setPadding(90, 0, 90, 0)
        mBinding.viewPagerTodaySong.setPageTransformer(getTransform())
        todaySongAdapter.submitList(
            listOf(
                Song("(G)I-DLE 'TOMBOY' Lyrics ((여자)아이들 TOMBOY 가사) (Color Coded Lyrics)", "https://i.ytimg.com/vi/E6W835snlNg/maxresdefault.jpg", ""),
                Song("BTS X Coldplay My Universe Lyrics (방탄소년단 콜드플레이 My Universe 가사) [Color Coded Lyrics/Han/Rom/Eng]", "https://i.ytimg.com/vi/nHKk8MTXgds/maxresdefault.jpg", ""),
                Song("\uD83C\uDFC6발매와 함께 빌보드 1위 달성 : Harry Styles - As It Was [가사/해석/번역/lyrics]", "https://i.ytimg.com/vi/OMRZevAb_jU/maxresdefault.jpg", "")
            )
        )
    }

    private fun setUpStudyRoom(myLocations: List<LocationInfo>) {
        val locationCheckAdapter = LocationCheckAdapter {
            shortToast("이동 : $it")
        }
        mBinding.recyclerLocationCheck.adapter = locationCheckAdapter
        locationCheckAdapter.submitList(myLocations)
    }

    companion object {
        fun getTransform() : CompositePageTransformer {
            val transform = CompositePageTransformer()
            transform.addTransformer(MarginPageTransformer(10))

            transform.addTransformer { view: View, fl: Float ->
                val v = 1 - abs(fl)
                view.scaleY = 0.8f + v * 0.2f
            }

            return transform
        }
    }
}