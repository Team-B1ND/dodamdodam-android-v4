package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutWriteBinding

class OutWriteFragment : BaseFragment<FragmentOutWriteBinding, OutWriteViewModel>() {
    override val viewModel: OutWriteViewModel by viewModels()

    override fun observerViewModel() {
    }
}
