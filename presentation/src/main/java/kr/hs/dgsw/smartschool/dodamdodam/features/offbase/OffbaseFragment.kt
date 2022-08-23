package kr.hs.dgsw.smartschool.dodamdodam.features.offbase

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOffbaseBinding

class OffbaseFragment : BaseFragment<FragmentOffbaseBinding, OffbaseViewModel>() {
    override val viewModel: OffbaseViewModel by viewModels()

    override fun observerViewModel() {
    }
}
