package kr.hs.dgsw.smartschool.dodamdodam.features.out

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter.OutListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class OutFragment : BaseFragment<FragmentOutBinding, OutViewModel>() {
    override val viewModel: OutViewModel by viewModels()

    private lateinit var outListAdapter: OutListAdapter

    override fun observerViewModel() {
        initOutListAdapter()
        collectOutList()

        bindingViewEvent { event ->
            when (event) {
                OutViewModel.ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }

    private fun collectOutList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getOutState.collect { state ->

                if (state.outList.isNotEmpty()) {
                    outListAdapter.submitList(state.outList)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }

            }
        }
    }

    private fun initOutListAdapter() {
        outListAdapter = OutListAdapter {
            shortToast("클릭")
        }

        mBinding.rvOutList.adapter = outListAdapter
    }
}
