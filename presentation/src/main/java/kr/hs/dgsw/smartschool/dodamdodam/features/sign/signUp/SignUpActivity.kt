package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamschool.R
import kr.hs.dgsw.smartschool.dodamschool.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamschool.databinding.ActivitySignUpBinding
import kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signin.SignInActivity


@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()
    override fun observerViewModel() {
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
                        SignUpViewModel.EVENT_ON_ERROR_ID -> {
                            Log.d("icecream", "EVENTONERRORID")
                            mBinding.layoutId.requestFocus()
                        }

                        SignUpViewModel.EVENT_ON_ERROR_PW -> {
                            Log.d("icecream", "EVENTONERRORPW")
                            mBinding.layoutPassword.requestFocus()
                        }

                        SignUpViewModel.EVENT_ON_ERROR_EMAIL -> {
                            mBinding.layoutEmail.requestFocus()
                        }
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                state.collect { state ->

                    if (state.result.isNotBlank()) {
                        Toast.makeText(this@SignUpActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        intent.putExtra("id", viewModel.id.value)
                        intent.putExtra("email", viewModel.email.value)
                        intent.putExtra("where", "signUp")
                        startActivity(intent)
                    }

                    if (state.isLoading) {
                        Log.d("StateLog", "SignUpActivity: loading")
                    }

                    if (state.error.isNotBlank()) {
                        Log.d("StateLog", "SignUpActivity: ${state.error}")
                        Toast.makeText(this@SignUpActivity, "${state.error}", Toast.LENGTH_SHORT).show()
                        return@collect
                    }
                }
            }
        }
    }

    private fun textChange(editText: EditText, layout: TextInputLayout, str: String) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                layout.error = when(str) {
                    "password" -> viewModel.setError(viewModel.password)
                    "id" -> viewModel.setError(viewModel.id)
                    "email" -> viewModel.setError(viewModel.email)
                    else -> "오류가 발생했습니다."
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
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
    }
}