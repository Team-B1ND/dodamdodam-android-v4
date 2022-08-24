package kr.hs.dgsw.smartschool.dodamdodam.features.auth.login

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignInBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.JoinActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivitySignInBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun observerViewModel() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                loginState.collect { state ->
                    if (state.error.isNotBlank()) {
                        Toast.makeText(this@LoginActivity, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        bindingViewEvent { event ->
            when (event) {
                LoginViewModel.EVENT_SUCCESS_SIGN_IN -> startMainActivity()
            }
        }

        mBinding.tvSignUp.setOnClickListener {
            startActivity(JoinActivity::class.java)
        }
    }

    private fun startMainActivity() {
        if (mBinding.checkAutoSignIn.isChecked)
            SharedPreferenceManager.signIn(this@LoginActivity)
        startActivityWithFinishAll(MainActivity::class.java)
    }
}
