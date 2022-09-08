package kr.hs.dgsw.smartschool.dodamdodam.features.out.detail

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutDetailBinding

@AndroidEntryPoint
class OutDetailFragment : BaseFragment<FragmentOutDetailBinding, OutDetailViewModel>() {

    override val viewModel: OutDetailViewModel by viewModels()

    override fun observerViewModel() {

    }
}