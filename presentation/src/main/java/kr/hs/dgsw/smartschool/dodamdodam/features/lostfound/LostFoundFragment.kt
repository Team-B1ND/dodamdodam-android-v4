package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding

class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>() {
    override val viewModel: LostFoundViewModel by viewModels()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {}

}