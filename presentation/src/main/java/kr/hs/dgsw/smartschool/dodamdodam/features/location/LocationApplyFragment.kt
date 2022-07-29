package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.adapter.PlaceAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLocationApplyBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.place.Place

@AndroidEntryPoint
class LocationApplyFragment : BaseFragment<FragmentLocationApplyBinding, LocationApplyViewModel>() {

    override val viewModel: LocationApplyViewModel by viewModels()
    private lateinit var placeAdapter: PlaceAdapter
    private lateinit var currentCheckedPlaces: ArrayList<Place>

    override fun observerViewModel() {
        val args: LocationApplyFragmentArgs by navArgs()
        currentCheckedPlaces = args.currentPlace.toCollection(ArrayList())
        initTimeTab()
        initPlaceRecyclerView()
        collectAllTime()
        collectPlace()
    }

    private fun initTimeTab() {
        viewModel.setCurrentTime(arguments?.getInt("timeTable")?.minus(1)?.rem(4) ?: 0)
        mBinding.layoutTimeTable.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.setCurrentTime(tab?.position ?: 0)
                PlaceAdapter.currentPlace.value = currentCheckedPlaces[viewModel.currentTime.value ?: 0]
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


    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@LocationApplyFragment) {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        LocationApplyViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                    }
                }
            }
        }
    }
}