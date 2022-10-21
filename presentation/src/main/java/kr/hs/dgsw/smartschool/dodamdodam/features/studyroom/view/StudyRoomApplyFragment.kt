package kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.view

import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.PlaceAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentStudyRoomApplyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.view.StudyRoomApplyFragment.Companion.ENABLE
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.viewmodel.StudyRoomApplyViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortSnack
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class StudyRoomApplyFragment :
    BaseFragment<FragmentStudyRoomApplyBinding, StudyRoomApplyViewModel>() {

    override val viewModel: StudyRoomApplyViewModel by viewModels()
    private lateinit var placeAdapter: PlaceAdapter

    companion object {
        private const val ENABLE: Boolean = true
        private const val UNABLE: Boolean = false
    }

    override fun observerViewModel() {
        initTimeTab()
        initViewEvent()
        initPlaceRecyclerView()
        collectAllTime()
        collectPlace()
        collectApplyLocation()
        collectMyLocation()

        viewModel.currentCheckPlaces.observe(this) {
            PlaceAdapter.currentPlace.value = it[viewModel.currentTime.value ?: 0]
        }
    }

    private fun initTimeTab() {
        // 1 2 3 4 5 6 7 8 의 타임테이블에서 1을 빼고 4를 나눠서 0 1 2 3 의 배열을 만족시키는 코드!
        viewModel.setCurrentTime(arguments?.getInt("timeTable")?.minus(1)?.rem(4) ?: 0)


        mBinding.layoutTimeTable.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab?.tag == ENABLE) {
                    viewModel.setCurrentTime(tab.position)
                    PlaceAdapter.currentPlace.value =
                        viewModel.currentCheckPlaces.value?.get(viewModel.currentTime.value ?: 0)
                    tab.select()
                } else {
                    tab?.removeBadge()
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun initPlaceRecyclerView() {
        placeAdapter = PlaceAdapter(this) {
            viewModel.changeLocation(it)
            PlaceAdapter.currentPlace.value = it
        }
        mBinding.rvPlace.adapter = placeAdapter
    }

    private fun collectAllTime() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllTimeState.collect { state ->
                if (state.timeTable.isNotEmpty()) {

                    val currentTime = Date().timeFormat()
                    viewModel.setTimeTable(state.timeTable)
                    var expiredIdx: Int = 0

                    state.timeTable.forEach { time ->
                        if (time.startTime >= currentTime) {
                            mBinding.layoutTimeTable.addTab(
                                mBinding.layoutTimeTable.newTab()
                                    .setText(time.name)
                                    .setTag(ENABLE)
                                , false
                            )
                        } else {
                            mBinding.layoutTimeTable.addTab(
                                mBinding.layoutTimeTable.newTab()
                                    .setText("만료")
                                    .setTag(UNABLE)
                                , false
                            )
                            expiredIdx += 1
                        }

                    }
                    val tabScript = mBinding.layoutTimeTable.getChildAt(0) as LinearLayout
                    for (i in 0 until expiredIdx) {
                        tabScript.getChildAt(i).setOnTouchListener { _, _ ->
                            true
                        }
                    }

                    mBinding.layoutTimeTable.getTabAt(viewModel.currentTime.value ?: 0)?.select()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectPlace() {
        lifecycleScope.launchWhenStarted {
            viewModel.getPlaceState.collect { state ->
                if (state.place.isNotEmpty()) {
                    mBinding.tvPlaceCount.text = "${state.place.size}개의 자습실"
                    placeAdapter.submitList(state.place)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectMyLocation() {
        lifecycleScope.launchWhenStarted {
            viewModel.getMyStudyRoomState.collect { state ->
                if (state.myStudyRooms.isNotEmpty()) {
                    viewModel.myStudyRoomList = state.myStudyRooms as ArrayList<StudyRoom>
                    viewModel.currentCheckPlaces.value = state.myStudyRooms.map(StudyRoom::place)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectApplyLocation() {
        lifecycleScope.launchWhenStarted {
            viewModel.applyStudyRoomState.collect { state ->
                if (state.message.isNotBlank()) {
                    mBinding.layout.shortSnack(state.message)
                    viewModel.getMyStudyRoom()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    viewModel.getMyStudyRoom()
                }
            }
        }
    }

    private fun initViewEvent() {
        bindingViewEvent { event ->
            when (event) {
                StudyRoomApplyViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                StudyRoomApplyViewModel.EVENT_NO_TIME -> shortToast("시간표가 없습니다.")
            }
        }
    }
}
