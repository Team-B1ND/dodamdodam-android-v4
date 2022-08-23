package kr.hs.dgsw.smartschool.dodamdodam.features.out

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutBinding

@AndroidEntryPoint
class OutFragment : BaseFragment<FragmentOutBinding, OutViewModel>() {
    override val viewModel: OutViewModel by viewModels()

    override fun observerViewModel() {
    }
}
