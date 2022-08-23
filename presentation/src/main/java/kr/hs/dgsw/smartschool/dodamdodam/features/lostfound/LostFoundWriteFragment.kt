package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundWriteBinding

class LostFoundWriteFragment : BaseFragment<FragmentLostFoundWriteBinding, LostFoundWriteViewModel>() {
    override val viewModel: LostFoundWriteViewModel by viewModels()

    override fun observerViewModel() {
    }
}
