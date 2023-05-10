package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentNightStudyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.adapter.NightStudyListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class NightStudyFragment : BaseFragment<FragmentNightStudyBinding, NightStudyViewModel>(), NightStudyListAdapter.NightStudyAction {

    override val viewModel: NightStudyViewModel by viewModels()
    private lateinit var nightStudyListAdapter: NightStudyListAdapter
    override fun observerViewModel() {
        setSwipeRefresh()
        initNightStudyListAdapter()
        viewModel.getMyNightStudy()
        collectNightStudyList()
        collectDeleteNightStudy()

        bindingViewEvent { event ->
            when (event) {
                NightStudyViewModel.ON_CLICK_BACK -> {
                    findNavController().popBackStack()
                }
                NightStudyViewModel.ON_CLICK_NIGHT_STUDY_WRITE -> {
                    val action = NightStudyFragmentDirections.actionNightStudyFragmentToNightStudyWriteFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun collectDeleteNightStudy() {
        lifecycleScope.launchWhenStarted {
            viewModel.nightStudyState.collect { state ->

                if (state.message.isNotBlank()) {
                    shortToast(state.message)
                    viewModel.getMyNightStudy()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectNightStudyList() {
        lifecycleScope.launchWhenStarted {
            viewModel.nightStudyState.collect { state ->
                if (state.isUpdate) {
                    if (state.nightStudyList.isEmpty()) {
                        mBinding.tvNoData.visibility = android.view.View.VISIBLE
                        nightStudyListAdapter.submitList(emptyList())
                    } else {
                        nightStudyListAdapter.submitList(state.nightStudyList)
                        mBinding.tvNoData.visibility = android.view.View.GONE
                    }
                    mBinding.swipeRefreshLayout.isRefreshing = false
                }
                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    mBinding.tvNoData.visibility = android.view.View.GONE
                    mBinding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    private fun initNightStudyListAdapter() {
        nightStudyListAdapter = NightStudyListAdapter(this)
        mBinding.rvOutList.adapter = nightStudyListAdapter
    }

    override fun onClickDelete(id: Int) {
        viewModel.deleteNightStudy(id)
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyNightStudy()
        }
    }
}
