package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpDetailBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInViewModel

@AndroidEntryPoint
class SignUpDetailActivity : BaseActivity<ActivitySignUpDetailBinding, SignUpDetailViewModel>() {
    override val viewModel: SignUpDetailViewModel by viewModels()

    override fun observerViewModel() {
        collectSignUpState()
        collectSignInState()

        mBinding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun collectSignUpState() {
        lifecycleScope.launchWhenStarted {
            viewModel.signUpState.collect { state ->
                if (state.result.isNotEmpty()) {
                    Toast.makeText(this@SignUpDetailActivity, state.result, Toast.LENGTH_SHORT).show()
                }

                if (state.isLoading) {
                    Toast.makeText(this@SignUpDetailActivity, "로딩중학교", Toast.LENGTH_SHORT).show()
                }

                if (state.error.isNotBlank()) {
                    Toast.makeText(this@SignUpDetailActivity, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun collectSignInState() {
        lifecycleScope.launchWhenStarted {
            viewModel.signInState.collect { state ->
                if (state.isLoading) {
                    Toast.makeText(this@SignUpDetailActivity, "로딩중학교 로그인", Toast.LENGTH_SHORT).show()
                }

                if (state.error.isNotBlank()) {
                    Toast.makeText(this@SignUpDetailActivity, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@SignUpDetailActivity) {
                it.getContentIfNotHandled()?.let { event ->
                    when(event) {
                        SignInViewModel.EVENT_SUCCESS_SIGN_IN -> {
                            val intent = Intent(this@SignUpDetailActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}