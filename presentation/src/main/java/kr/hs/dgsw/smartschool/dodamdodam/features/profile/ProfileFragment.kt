package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.data.util.Constants
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    override fun observerViewModel() {
        mBinding.cardSetting.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_settingFragment)
        }

        collectMyInfo()
    }

    private fun collectMyInfo() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                myInfoState.collect { state ->
                    if (myInfoState.value.myInfo != null) {
                        with(myInfoState.value.myInfo!!) {
                            val generation = "%d%d%02d".format(grade, room, number)
                            setProfileInfo(generation, name, email, profileImage ?: "")
                        }
                    }

                    if (state.error.isNotBlank()) {
                        setProfileInfo("", "값을 받아올 수 없습니다.", "", "")
                    }
                }
            }
        }
    }

    private fun setProfileInfo(generation: String, name: String, email: String, profileImage: String) {
        mBinding.tvGeneration.text = generation
        mBinding.tvId.text = name
        mBinding.tvEmail.text = email

        Glide.with(mBinding.root)
            .load(Constants.SERVER_HOST + profileImage)
            .centerCrop()
            .error(R.drawable.default_user)
            .into(mBinding.ivProfile)
    }

}