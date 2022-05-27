package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment


class SignUpFragment1 : BaseFragment<ItemSignUp1Binding,SignUpViewModel>(){
    override val viewModel: SignUpViewModel by viewModels()
   override fun observerViewModel(){
        setSpinner()
        with(mBinding) {
            textChange(edittextId, layoutId, "id")
            textChange(edittextPassword, layoutPassword, "password")
            textChange(edittextEmail, layoutEmail, "email")
        }
        with(viewModel) {
            viewEvent.observe(this@SignUpActivity) { it ->
                it.getContentIfNotHandled()?.let { event ->
                    when(event) {
                        kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp.SignUpViewModel.EVENT_ON_ERROR_ID -> {
                            android.util.Log.d("icecream", "EVENTONERRORID")
                            mBinding.layoutId.requestFocus()
                        }

                        kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp.SignUpViewModel.EVENT_ON_ERROR_PW -> {
                            android.util.Log.d("icecream", "EVENTONERRORPW")
                            mBinding.layoutPassword.requestFocus()
                        }

                        kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp.SignUpViewModel.EVENT_ON_ERROR_EMAIL -> {
                            mBinding.layoutEmail.requestFocus()
                        }
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                state.collect { state ->

                    if (state.result.isNotBlank()) {
                        android.widget.Toast.makeText(this@SignUpActivity, "회원가입 성공", android.widget.Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        intent.putExtra("id", viewModel.id.value)
                        intent.putExtra("email", viewModel.email.value)
                        intent.putExtra("where", "signUp")
                        startActivity(intent)
                    }

                    if (state.isLoading) {
                        android.util.Log.d("StateLog", "SignUpActivity: loading")
                    }

                    if (state.error.isNotBlank()) {
                        android.util.Log.d("StateLog", "SignUpActivity: ${state.error}")
                        android.widget.Toast.makeText(this@SignUpActivity, "${state.error}", android.widget.Toast.LENGTH_SHORT).show()
                        return@collect
                    }
                }
            }
        }
    }

}