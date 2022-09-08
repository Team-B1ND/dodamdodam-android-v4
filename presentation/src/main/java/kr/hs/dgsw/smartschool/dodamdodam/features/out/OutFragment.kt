package kr.hs.dgsw.smartschool.dodamdodam.features.out

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter.OutListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.out.etc.OutState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class OutFragment : BaseFragment<FragmentOutBinding, OutViewModel>(), OutListAdapter.OutAction {
    override val viewModel: OutViewModel by viewModels()

    private lateinit var outListAdapter: OutListAdapter

    override fun observerViewModel() {
        setSwipeRefresh()
        initOutListAdapter()
        viewModel.getOutByDate()
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
            viewModel.getOutByDateState.collect { state ->

                if (state.isUpdate) {
                    if (state.outList.isEmpty()) {
                        mBinding.tvNoData.visibility = View.VISIBLE
                    } else {
                        outListAdapter.submitList(state.outList)
                        mBinding.tvNoData.visibility = View.GONE
                    }

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
                    viewModel.getOutByDate()
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
                    viewModel.getOutByDate()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun initOutListAdapter() {
        outListAdapter = OutListAdapter(this)
        mBinding.rvOutList.adapter = outListAdapter
    }

    override fun onClickDelete(state: OutState, id: Int) {
        if (state == OutState.OutGoing)
            viewModel.deleteOutGoing(id)
        else
            viewModel.deleteOutSleeping(id)
    }

    override fun onClickItem(state: OutState, id: Int) {
        val action: NavDirections = if (state == OutState.OutGoing)
            OutFragmentDirections.actionOutFragmentToOutDetailFragment(
                isOutSleeping = false,
                id = id
            )
        else
            OutFragmentDirections.actionOutFragmentToOutDetailFragment(
                isOutSleeping = true,
                id = id
            )
        findNavController().navigate(action)
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getOutByDate()
        }
    }

    private fun endRefreshing() {
        mBinding.swipeRefreshLayout.isRefreshing = false
    }

}
