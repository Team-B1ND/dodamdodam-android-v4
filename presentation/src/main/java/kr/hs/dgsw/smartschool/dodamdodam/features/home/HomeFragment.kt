package kr.hs.dgsw.smartschool.dodamdodam.features.home

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val hasActionBar = false
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {

    }

}