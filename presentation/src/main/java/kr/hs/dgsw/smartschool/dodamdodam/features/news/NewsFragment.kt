package kr.hs.dgsw.smartschool.dodamdodam.features.news

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentNewsBinding

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {
    override val viewModel: NewsViewModel by viewModels()
    override val hasActionBar: Boolean = true
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {

    }

}