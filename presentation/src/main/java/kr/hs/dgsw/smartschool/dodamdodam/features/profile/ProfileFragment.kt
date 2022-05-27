package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasActionBar: Boolean = true
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {

    }

}