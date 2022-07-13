package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentEditProfileBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getExtension

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>() {
    override val viewModel: EditProfileViewModel by viewModels()
    private val args: EditProfileFragmentArgs by navArgs()

    override fun observerViewModel() {
        setInitValue()

        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        correctEditProfileResult()
    }

    private fun correctEditProfileResult() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                editProfileState.collect { state ->

                    if (state.message.isNotEmpty()) {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }

                    if (state.error.isNotBlank()) {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setInitValue() {
        with(viewModel) {
            email.value = args.email
            phone.value = args.phone
            memberId = args.memberId
            profileOriginalName.value = args.profileImage
            profileUploadName.value = args.profileImage
            type = args.profileImage.getExtension()

            Glide.with(mBinding.root)
                .load(args.profileImage)
                .error(R.drawable.default_user)
                .centerCrop()
                .into(mBinding.ivProfile)
        }
    }

}