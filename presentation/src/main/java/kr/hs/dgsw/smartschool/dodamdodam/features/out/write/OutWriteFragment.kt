package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutWriteBinding

class OutWriteFragment : BaseFragment<FragmentOutWriteBinding, OutWriteViewModel>() {
    override val viewModel: OutWriteViewModel by viewModels()

    override fun observerViewModel() {

        bindingViewEvent { event ->
            when (event) {
                OutWriteViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }
}
