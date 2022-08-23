package kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignInBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.up.SignUpActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {
    override val viewModel: SignInViewModel by viewModels()

    override fun observerViewModel() {
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                signInState.collect { state ->
                    if (state.error.isNotBlank()) {
                        Toast.makeText(this@SignInActivity, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        bindingViewEvent { event ->
            when (event) {
                SignInViewModel.EVENT_SUCCESS_SIGN_IN -> startMainActivity()
            }
        }

        mBinding.tvSignUp.setOnClickListener {
            startActivity(SignUpActivity::class.java)
        }
    }

    private fun startMainActivity() {
        if (mBinding.checkAutoSignIn.isChecked)
            SharedPreferenceManager.signIn(this@SignInActivity)
        startActivityWithFinishAll(MainActivity::class.java)
    }
}
