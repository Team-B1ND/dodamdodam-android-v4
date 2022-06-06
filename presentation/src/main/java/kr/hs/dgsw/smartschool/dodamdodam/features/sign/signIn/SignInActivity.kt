package kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signin

import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamschool.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamschool.databinding.ActivitySignInBinding
import kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signup.SignUpActivity

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {
    override val viewModel: SignInViewModel by viewModels()
    override fun observerViewModel() {
        with(viewModel) {
            viewEvent.observe(this@SignInActivity) { it ->
                 it.getContentIfNotHandled()?.let { event ->
                     when(event) {
                         SignInViewModel.EVENT_ON_CLICK_SIGN_UP -> {
                             startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                         }
                     }
                 }
            }
        }
    }
}