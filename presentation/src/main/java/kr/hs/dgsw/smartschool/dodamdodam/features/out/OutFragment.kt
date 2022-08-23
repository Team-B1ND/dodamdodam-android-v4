package kr.hs.dgsw.smartschool.dodamdodam.features.out

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class OutFragment : BaseFragment<FragmentOutBinding, OutViewModel>() {
    override val viewModel: OutViewModel by viewModels()

    override fun observerViewModel() {

        collectOutList()
    }

    private fun collectOutList() {
        lifecycleScope.launchWhenStarted {
            viewModel.getOutState.collect { state ->

                if (state.outList.isNotEmpty()) {
                    val outList = state.outList
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }

            }
        }
    }
}
