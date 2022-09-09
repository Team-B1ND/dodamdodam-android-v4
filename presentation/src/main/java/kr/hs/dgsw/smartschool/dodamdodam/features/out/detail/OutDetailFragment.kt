package kr.hs.dgsw.smartschool.dodamdodam.features.out.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutDetailBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit.EditProfileFragmentArgs
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

@AndroidEntryPoint
class OutDetailFragment : BaseFragment<FragmentOutDetailBinding, OutDetailViewModel>() {

    override val viewModel: OutDetailViewModel by viewModels()

    private val args: OutDetailFragmentArgs by navArgs()

    override fun observerViewModel() {
        viewModel.getDetailOut(args.isOutSleeping, args.id)
        collectOutDetail()

        bindingViewEvent { event ->
            when (event) {
                OutDetailViewModel.EVENT_ON_CLICK_UPDATE -> {
                    val action = OutDetailFragmentDirections.actionOutDetailFragmentToOutWriteFragment(viewModel.outItem.value ?: return@bindingViewEvent)
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun collectOutDetail() {
        lifecycleScope.launchWhenStarted {
            with(viewModel) {
                getOutDetailState.collect { state ->
                    if (state.error.isNotBlank())
                        shortToast(state.error)
                }
            }
        }
    }
}