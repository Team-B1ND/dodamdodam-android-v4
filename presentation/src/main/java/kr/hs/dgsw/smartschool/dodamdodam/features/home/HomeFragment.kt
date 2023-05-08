package kr.hs.dgsw.smartschool.dodamdodam.features.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.home.adapter.BannerAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.home.adapter.MealHomeAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.SongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.adapter.StudyRoomCheckAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openUrlWithBrowser
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openVideoFromUrl
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.model.meal.MealInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private var date: LocalDate = LocalDate.now()

    private lateinit var studyRoomCheckAdapter: StudyRoomCheckAdapter
    private lateinit var songAdapter: SongAdapter
    lateinit var mealHomeAdapter: MealHomeAdapter
    private lateinit var bannerAdapter: BannerAdapter

    override fun observerViewModel() {
        initViewEvent()

        setLocationRecyclerView()
        setMealListViewPager()
        setUpTodaySong()
        setBannerViewPager()

        viewModel.getAllowSong()
        viewModel.getMyStudyRoom()
        viewModel.getActiveBanner()

        collectMyStudyRoom()
        collectMealState()
        collectSongList()
        collectBannerState()
    }

    private fun initViewEvent() {
        bindingViewEvent { event ->
            when (event) {
                HomeViewModel.ON_CLICK_MEAL_MORE -> (activity as? MainActivity)?.moveHomeToMeal()
                HomeViewModel.ON_CLICK_SONG_MORE -> (activity as? MainActivity)?.moveHomeToSong()
                HomeViewModel.ON_CLICK_OUT -> findNavController().navigate(R.id.action_main_home_to_outFragment)
                HomeViewModel.ON_CLICK_ITMAP -> findNavController().navigate(R.id.action_main_home_to_itMapFragment)
                HomeViewModel.ON_CLICK_EVENING_STUDY -> findNavController().navigate(R.id.action_main_home_to_eveningStudyFragment)
            }
        }
    }

    private fun collectMyStudyRoom() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getMyStudyRoomState.collect { state ->
                    if (state.isUpdate) {
                        studyRoomCheckAdapter.submitList(state.myStudyRooms)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    private fun collectBannerState() = with(viewModel) {
        lifecycleScope.launchWhenStarted {
            getActiveBannerState.collect { state ->
                if (state.activeBanners.isNotEmpty()) {
                    bannerAdapter.submitList(state.activeBanners)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectMealState() = with(viewModel) {
        if (LocalDateTime.now().hour >= 20) {
            date = LocalDate.now()
            date = date.plusDays(1)
        }
        getMeal(date)

        lifecycleScope.launchWhenStarted {
            getMealState.collect { state ->
                if (state.isUpdate) {
                    state.meal?.let {
                        setMealList(it)
                    }
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

    private fun collectSongList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllowSongState.collect { state ->
                if (state.songList.isNotEmpty()) {
                    songAdapter.submitList(state.songList)
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
            mBinding.ivMusicalNote.visibility = View.VISIBLE
            mBinding.rvTodaySong.visibility = View.GONE
        } else {
            mBinding.tvEmptySong.visibility = View.GONE
            mBinding.ivMusicalNote.visibility = View.GONE
            mBinding.rvTodaySong.visibility = View.VISIBLE
        }
    }

    private fun setMealListViewPager() {
        mealHomeAdapter = MealHomeAdapter {
            (activity as? MainActivity)?.moveHomeToMeal()
        }
        mBinding.viewPagerMealList.adapter = mealHomeAdapter
        mBinding.viewPagerMealList.offscreenPageLimit = 3
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
                currentTime < "09:00" -> minOf(0)
                currentTime < "13:20" -> minOf(1)
                currentTime < "20:00" -> minOf(2)
                else -> minOf(0)
            }
        }
    }

    private fun setUpTodaySong() {
        songAdapter = SongAdapter { url ->
            this@HomeFragment.openVideoFromUrl(url)
        }
        mBinding.rvTodaySong.adapter = songAdapter
    }

    private fun setLocationRecyclerView() {
        studyRoomCheckAdapter = StudyRoomCheckAdapter { position ->
            val action = HomeFragmentDirections.actionMainHomeToStudyRoomApplyFragment(position)
            findNavController().navigate(action)
        }
        mBinding.recyclerLocationCheck.adapter = studyRoomCheckAdapter
    }

    private fun setBannerViewPager() {
        bannerAdapter = BannerAdapter { url ->
            this.openUrlWithBrowser(url)
        }
        mBinding.vpBanner.adapter = bannerAdapter
        mBinding.indicatorBanner.attachTo(mBinding.vpBanner)
    }
}
