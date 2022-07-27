package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLocationApplyBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class LocationApplyFragment : BaseFragment<FragmentLocationApplyBinding, LocationApplyViewModel>() {

    override val viewModel: LocationApplyViewModel by viewModels()

    override fun observerViewModel() {
        viewModel.setCurrentTime(arguments?.getInt("timeTable")?.minus(1)?.rem(4) ?: 0)
        mBinding.layoutTimeTable.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.setCurrentTime(tab?.position ?: 0)
                tab?.select()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        collectAllTime()

       /* mBinding.layoutTimeTable.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentTime.value = tab?.position
                PlaceAdapter.currentPlace.value =
                    currentCheckedPlaces.value?.get(currentTime.value ?: 0)
                tab?.select()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })*/

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

    override fun bindingViewEvent() {}
}