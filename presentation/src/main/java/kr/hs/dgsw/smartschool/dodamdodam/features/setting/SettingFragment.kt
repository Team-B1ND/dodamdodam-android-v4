package kr.hs.dgsw.smartschool.dodamdodam.features.setting

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSettingBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModel: SettingViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        mBinding.tvSignOut.setOnClickListener {
            SharedPreferenceManager.signOut(requireContext())
            startActivityWithFinishAll(SignInActivity::class.java)
        }
    }

    override fun bindingViewEvent() {}
}