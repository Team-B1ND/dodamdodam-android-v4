package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentProfileBinding

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    var email: String = ""
    var phone: String = ""
    var memberId: String = ""
    var profileImage: String = ""

    override fun observerViewModel() {
        mBinding.cardSetting.setOnClickListener {
            findNavController().navigate(R.id.action_main_profile_to_settingFragment)
        }

        setSwipeRefresh()
        collectMyInfo()
        goEditProfile()
    }

    private fun collectMyInfo() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                myInfoState.collect { state ->
                    if (myInfoState.value.myInfo != null) {
                        with(myInfoState.value.myInfo!!) {
                            val generation = "%d%d%02d".format(grade, room, number)
                            setProfileInfo(generation, name, email, profileImage ?: "")
                            setNavData(email, phone, id, profileImage ?: "")
                            endRefreshing()
                        }

                        mBinding.btnGoInfoUpdate.visibility = View.VISIBLE
                    }

                    if (state.error.isNotBlank()) {
                        setProfileInfo("", "값을 받아올 수 없습니다.", "", "")
                        mBinding.btnGoInfoUpdate.visibility = View.GONE
                        endRefreshing()
                    }
                }
            }
        }
    }

    private fun goEditProfile() {
        mBinding.btnGoInfoUpdate.setOnClickListener {
            val navAction = ProfileFragmentDirections.actionMainProfileToEditProfileFragment(email, phone, profileImage, memberId)
            findNavController().navigate(navAction)
        }
    }

    private fun setProfileInfo(generation: String, name: String, email: String, profileImage: String) {
        mBinding.tvGeneration.text = generation
        mBinding.tvId.text = name
        mBinding.tvEmail.text = email

        Glide.with(mBinding.root)
            .load(profileImage)
            .centerCrop()
            .error(R.drawable.default_user)
            .into(mBinding.ivProfile)
    }

    private fun setNavData(email: String, phone: String, memberId: String, profileImage: String) {
        this.email = email
        this.phone = phone
        this.memberId = memberId
        this.profileImage = profileImage
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyInfo()
        }
    }

    private fun endRefreshing() {
        mBinding.swipeRefreshLayout.isRefreshing = false
    }
}