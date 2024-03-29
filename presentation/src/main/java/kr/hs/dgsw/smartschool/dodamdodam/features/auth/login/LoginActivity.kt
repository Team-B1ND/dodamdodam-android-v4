package kr.hs.dgsw.smartschool.dodamdodam.features.auth.login

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityLoginBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.JoinActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun observerViewModel() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                loginState.collect { state ->

                    if (state.isSuccess) {
                        startMainActivity()
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }

        bindingViewEvent { event ->
            when (event) {
                LoginViewModel.EVENT_ON_CLICK_JOIN -> startActivity(JoinActivity::class.java)
                LoginViewModel.EVENT_ON_CLICK_BACK -> finish()
            }
        }
    }

    private fun startMainActivity() {
        SharedPreferenceManager.login(this@LoginActivity)
        startActivityWithFinishAll(MainActivity::class.java)
    }
}
