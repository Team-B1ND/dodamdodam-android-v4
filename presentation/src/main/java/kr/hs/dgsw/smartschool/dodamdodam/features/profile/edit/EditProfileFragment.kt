package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentEditProfileBinding

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {
    override val viewModel: EditProfileViewModel by viewModels()

    override fun observerViewModel() {

    }

}