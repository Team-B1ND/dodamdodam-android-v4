package kr.hs.dgsw.smartschool.dodamdodam.features.setting

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSettingBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.LoginActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openUrlWithBrowser
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    override val viewModel: SettingViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        mBinding.btnService.setOnClickListener{
            this.openUrlWithBrowser(resources.getString(R.string.link_service_policy))
        }
        mBinding.btnPersonal.setOnClickListener{
            this.openUrlWithBrowser(resources.getString(R.string.link_personal_info))
        }
        mBinding.switchDarkMode.setOnCheckedChangeListener{ _, isChecked ->
            when(isChecked){
                true ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                false ->{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        }

        mBinding.btnLogOut.setOnClickListener {
            SharedPreferenceManager.logout(requireContext())
            startActivityWithFinishAll(LoginActivity::class.java)
        }
    }
}
