package kr.hs.dgsw.smartschool.dodamdodam.features.news

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentMyNewsBinding

class MyNewsFragment : BaseFragment<FragmentMyNewsBinding, MyNewsViewModel>() {
    override val viewModel: MyNewsViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {}
}