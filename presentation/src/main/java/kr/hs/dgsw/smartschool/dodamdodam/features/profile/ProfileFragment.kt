package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
        mBinding.cardBus.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_busFragment)
        }

    }

}