package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.PlaceAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLocationApplyBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortSnack
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo

@AndroidEntryPoint
class LocationApplyFragment : BaseFragment<FragmentLocationApplyBinding, LocationApplyViewModel>() {

    override val viewModel: LocationApplyViewModel by viewModels()
    private lateinit var placeAdapter: PlaceAdapter


    override fun observerViewModel() {
        initTimeTab()
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
        viewModel.setCurrentTime(arguments?.getInt("timeTable")?.minus(1)?.rem(4) ?: 0)
        mBinding.layoutTimeTable.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.setCurrentTime(tab?.position ?: 0)
                PlaceAdapter.currentPlace.value = viewModel.currentCheckPlaces.value?.get(viewModel.currentTime.value ?: 0)
                tab?.select()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun initPlaceRecyclerView() {
        placeAdapter = PlaceAdapter(this) {
            viewModel.changeLocation(it)
        }
        mBinding.rvPlace.adapter = placeAdapter
    }

    private fun collectAllTime() {
        lifecycleScope.launchWhenStarted {
            viewModel.getAllTimeState.collect { state ->
                if (state.timeTable.isNotEmpty()) {
                    viewModel.setTimeTable(state.timeTable)
                    state.timeTable.forEach { time ->
                        mBinding.layoutTimeTable.addTab(
                            mBinding.layoutTimeTable.newTab().setText(time.name), false
                        )
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
            viewModel.getMyLocationState.collect { state ->
                if (state.myLocations.isNotEmpty()) {
                    viewModel.myLocationInfoList = state.myLocations as ArrayList<LocationInfo>
                    viewModel.currentCheckPlaces.value = state.myLocations.map(LocationInfo::place)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectApplyLocation() {
        lifecycleScope.launchWhenStarted {
            viewModel.applyLocationState.collect { state ->
                if (state.message.isNotBlank()) {
                    mBinding.layout.shortSnack(state.message)
                    viewModel.getMyLocation(false)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    viewModel.getMyLocation()
                }
            }
        }
    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@LocationApplyFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        LocationApplyViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                        LocationApplyViewModel.EVENT_NO_TIME -> shortToast("시간표가 없습니다.")
                    }
                }
            }
        }
    }
}