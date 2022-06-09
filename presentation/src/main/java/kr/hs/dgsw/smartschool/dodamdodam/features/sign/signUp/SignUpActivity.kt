package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import androidx.activity.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.SignUpPagerAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpBinding


class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()
    val signUpPagerAdapter =  SignUpPagerAdapter(this)
    override fun observerViewModel() {
        with(mBinding){
            viewPager.adapter = signUpPagerAdapter
        }
    }
    /*setSpinner()
    with(mBinding) {
        textChange(edittextId, layoutId, "id")
        textChange(edittextPassword, layoutPassword, "password")
        textChange(edittextEmail, layoutEmail, "email")
    }
    with(viewModel) {
        viewEvent.observe(this) { it ->
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
                    android.widget.Toast.makeText(this, "회원가입 성공", android.widget.Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SignInActivity::class.java)
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
                    android.widget.Toast.makeText(this, "${state.error}", android.widget.Toast.LENGTH_SHORT).show()
                    return@collect
                }
            }
        }
    }
}
private fun setSpinner() {
    var yearSpinner : Spinner = mBinding.generationSpinner

    ArrayAdapter.createFromResource(
        this,
        R.array.generation_array,
        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearSpinner.onItemSelectedListener
        yearSpinner.adapter = adapter
        yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                //TODO(ViewModel에 값 넘겨주기)
                viewModel.generation.value = (pos+1).toString()
            }

        }
    }
}*/
}