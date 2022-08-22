package kr.hs.dgsw.smartschool.dodamdodam.features.news

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentAllNewsBinding

class AllNewsFragment : BaseFragment<FragmentAllNewsBinding, AllNewsViewModel>() {
    override val viewModel: AllNewsViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
    }
}
