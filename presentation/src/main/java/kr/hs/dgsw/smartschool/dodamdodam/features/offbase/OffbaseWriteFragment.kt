package kr.hs.dgsw.smartschool.dodamdodam.features.offbase

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOffbaseBinding

class OffbaseWriteFragment: BaseFragment<FragmentOffbaseBinding, OffbaseWriteViewModel>() {
    override val viewModel: OffbaseWriteViewModel by viewModels()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {}

}