package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.annotation.SuppressLint
import android.view.Gravity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LocationCheckAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.TodaySongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.util.ViewPagerUtils.getTransform
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import kr.hs.dgsw.smartschool.domain.model.song.Song
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var mealList = listOf<Meal>()
    private var date: LocalDate = LocalDate.now()
    private lateinit var mealHomeAdapter: MealHomeAdapter

    override fun observerViewModel() {
        setUpTodaySong()
        setMealListViewPager()
        collectMealState()
        collectDataSetUpDate()
        collectMyLocation()
    }


    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@HomeFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        HomeViewModel.ON_CLICK_MEAL_MORE -> (activity as? MainActivity)?.moveHomeToMeal()
                        HomeViewModel.ON_CLICK_SONG_MORE -> (activity as? MainActivity)?.moveHomeToSong()
                        HomeViewModel.ON_CLICK_MENU -> openDrawer()
                    }
                }
            }
        }
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
                    if (state.meal.isNotEmpty()) {
                        mealList = mealState.value.meal
                        getMeal(mealList, date)
                    }
                    if (state.error.isNotBlank()) {
                        setMealList(Meal("값을 받아올 수 없습니다.", "", "값을 받아올 수 없습니다.", false, "값을 받아올 수 없습니다."))
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun getMeal(mealList: List<Meal>, date: LocalDate) {
        val meal = mealList.find { meal ->
            meal.date == date.toString()
        }
        meal?.let {
            setMealList(it)
        }
    }

    private fun setMealListViewPager() {
        mealHomeAdapter = MealHomeAdapter()
        mBinding.viewPagerMealList.adapter = mealHomeAdapter
        mBinding.viewPagerMealList.offscreenPageLimit = 3
        mBinding.viewPagerMealList.setPadding(90, 0, 90, 0)
        mBinding.viewPagerMealList.setPageTransformer(getTransform())
    }

    private fun setMealList(meal: Meal) {
        mealHomeAdapter.submitList(listOf(
            MealInfo(1, meal.safeBreakfast),
            MealInfo(2, meal.safeLunch),
            MealInfo(3, meal.safeDinner)
        ))

        val currentTime = Date().timeFormat()
        mBinding.viewPagerMealList.postDelayed({
            mBinding.viewPagerMealList.currentItem = when {
                currentTime < "09:00" -> minOf(0, mealList.size - 1)
                currentTime < "13:20" -> minOf(1, mealList.size - 1)
                else -> minOf(2, mealList.size - 1)
            }
       }, 800)
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
            val action = HomeFragmentDirections.actionMainHomeToStudyRoomApplyFragment(it)
            findNavController().navigate(action)
        }
        mBinding.recyclerLocationCheck.adapter = locationCheckAdapter
        locationCheckAdapter.submitList(myLocations)
    }
}