package kr.hs.dgsw.smartschool.dodamdodam.features.out

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter.OutListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class OutFragment : BaseFragment<FragmentOutBinding, OutViewModel>() {
    override val viewModel: OutViewModel by viewModels()

    private lateinit var outListAdapter: OutListAdapter

    override fun observerViewModel() {
        setSwipeRefresh()
        initOutListAdapter()
        collectOutList()
        collectDeleteOutGoing()
        collectDeleteOutSleeping()

        bindingViewEvent { event ->
            when (event) {
                OutViewModel.ON_CLICK_BACK -> findNavController().popBackStack()
                OutViewModel.ON_CLICK_OUT_WRITE -> findNavController().navigate(R.id.action_outFragment_to_outWriteFragment)
            }
        }
    }

    private fun collectOutList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getOutState.collect { state ->

                if (state.outList.isNotEmpty()) {
                    outListAdapter.submitList(state.outList)
                    mBinding.tvNoData.visibility = View.GONE
                    endRefreshing()
                }

                if (state.isEmptyList) {
                    mBinding.tvNoData.visibility = View.VISIBLE
                    endRefreshing()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    mBinding.tvNoData.visibility = View.GONE
                    endRefreshing()
                }
            }
        }
    }

    private fun collectDeleteOutGoing() {
        lifecycleScope.launchWhenStarted {
            viewModel.deleteOutGoingState.collect { state ->

                if (state.message.isNotBlank()) {
                    shortToast(state.message)
                    viewModel.getMyOutApplies()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectDeleteOutSleeping() {
        lifecycleScope.launchWhenStarted {
            viewModel.deleteOutSleepingState.collect { state ->

                if (state.message.isNotBlank()) {
                    shortToast(state.message)
                    viewModel.getMyOutApplies()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun initOutListAdapter() {
        outListAdapter = OutListAdapter { state, idx ->
            if (state == 0)
                viewModel.deleteOutGoing(idx)
            else
                viewModel.deleteOutSleeping(idx)
        }

        mBinding.rvOutList.adapter = outListAdapter
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyOutApplies()
        }
    }

    private fun endRefreshing() {
        mBinding.swipeRefreshLayout.isRefreshing = false
    }
}
