package kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignInBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity

@AndroidEntryPoint
class SignInActivity : BaseActivity<ActivitySignInBinding, SignInViewModel>() {
    override val viewModel: SignInViewModel by viewModels()

    override fun observerViewModel() {
        bindingViewEvent()
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                signInState.collect { state ->
                    if (state.isLoading) {
                        Toast.makeText(this@SignInActivity, "로딩중학교", Toast.LENGTH_SHORT).show()
                    }

                    if (state.error.isNotBlank()) {
                        Toast.makeText(this@SignInActivity, state.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@SignInActivity) {
                it.getContentIfNotHandled()?.let { event ->
                    when(event) {
                        SignInViewModel.EVENT_SUCCESS_LOGIN -> {
                            val intent = Intent(this@SignInActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

}