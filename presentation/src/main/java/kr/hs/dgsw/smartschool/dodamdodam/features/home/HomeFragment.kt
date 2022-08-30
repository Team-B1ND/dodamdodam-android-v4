package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LocationCheckAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.adapter.SongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.util.ViewPagerUtils.getTransform
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openVideoFromUrl
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import kr.hs.dgsw.smartschool.domain.model.song.VideoYoutubeData
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var mealList = listOf<Meal>()
    private var date: LocalDate = LocalDate.now()

    private lateinit var locationCheckAdapter: LocationCheckAdapter
    private lateinit var songAdapter: SongAdapter
    lateinit var mealHomeAdapter: MealHomeAdapter

    override fun observerViewModel() {
        setLocationRecyclerView()
        setUpTodaySong()
        setMealListViewPager()
        collectMealState()
        collectDataSetUpDate()
        collectSongList()
        initViewEvent()
    }

    private fun initViewEvent() {
        bindingViewEvent { event ->
            when (event) {
                HomeViewModel.ON_CLICK_MEAL_MORE -> (activity as? MainActivity)?.moveHomeToMeal()
                HomeViewModel.ON_CLICK_SONG_MORE -> (activity as? MainActivity)?.moveHomeToSong()
                HomeViewModel.ON_CLICK_OUT -> findNavController().navigate(R.id.action_main_home_to_outFragment)
                HomeViewModel.ON_CLICK_LOST -> findNavController().navigate(R.id.action_main_home_to_lostFoundFragment)
            }
        }
    }

    private fun collectDataSetUpDate() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                dataSetUpState.collect { state ->
                    if (state.result != null) {
                        getMyLocation()
                        collectMyLocation()
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
                        state.myLocations.forEach {
                            Log.d("TestTest", "collectMyLocation: ${it.place?.name}")
                        }
                        locationCheckAdapter.submitList(state.myLocations)
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
            if (LocalDateTime.now().hour >= 20) {
                date = LocalDate.now()
                date = date.plusDays(1)
                mBinding.tvMealTitle.text = "내일의 급식"
            }
            mBinding.tvMealDate.text = String.format("%d.%d", date.monthValue, date.dayOfMonth)
            getMealList(date)

            lifecycleScope.launchWhenStarted {
                getMealState.collect { state ->
                    if (state.meal.isNotEmpty()) {
                        mealList = getMealState.value.meal
                        getMeal(mealList, date)
                    }
                    if (state.error.isNotBlank()) {
                        setMealList(
                            Meal(
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

    private fun collectSongList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllowSongState.collect { state ->
                if (state.songList.isNotEmpty()) {
                    songAdapter.submitList(state.songList.mapNotNull(VideoYoutubeData::source))
                    setEmptySongView(false)
                } else {
                    setEmptySongView(true)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    setEmptySongView(true)
                }
            }
        }
    }

    private fun setEmptySongView(isEmptySongList: Boolean) {
        if (isEmptySongList) {
            mBinding.tvEmptySong.visibility = View.VISIBLE
            mBinding.viewPagerTodaySong.visibility = View.GONE
        } else {
            mBinding.tvEmptySong.visibility = View.GONE
            mBinding.viewPagerTodaySong.visibility = View.VISIBLE
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
        mealHomeAdapter.submitList(
            listOf(
                MealInfo(1, meal.safeBreakfast),
                MealInfo(2, meal.safeLunch),
                MealInfo(3, meal.safeDinner)
            )
        )
        changeMealPageToTime()
    }

    private fun changeMealPageToTime() {
        val currentTime = Date().timeFormat()
        mBinding.viewPagerMealList.post {
            mBinding.viewPagerMealList.currentItem = when {
                currentTime < "09:00" -> minOf(0, mealList.size - 1)
                currentTime < "13:20" -> minOf(1, mealList.size - 1)
                currentTime < "20:00" -> minOf(2, mealList.size - 1)
                else -> minOf(0, mealList.size - 1)
            }
        }
    }

    private fun setUpTodaySong() {
        songAdapter = SongAdapter { url ->
            this@HomeFragment.openVideoFromUrl(url)
        }
        mBinding.viewPagerTodaySong.adapter = songAdapter
        mBinding.viewPagerTodaySong.offscreenPageLimit = 3
        mBinding.viewPagerTodaySong.setPadding(90, 0, 90, 0)
        mBinding.viewPagerTodaySong.setPageTransformer(getTransform())
    }

    private fun setLocationRecyclerView() {
        locationCheckAdapter = LocationCheckAdapter { position ->
            val action = HomeFragmentDirections.actionMainHomeToStudyRoomApplyFragment(position)
            findNavController().navigate(action)
        }
        mBinding.recyclerLocationCheck.adapter = locationCheckAdapter
    }
}
