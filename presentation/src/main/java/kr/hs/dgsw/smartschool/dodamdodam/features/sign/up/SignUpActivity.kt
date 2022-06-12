package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import android.content.Intent
import androidx.activity.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()

    override fun observerViewModel() {

    }

    override fun bindingViewEvent() {
        with(viewModel) {
            viewEvent.observe(this@SignUpActivity) {
                it.getContentIfNotHandled()?.let { event ->
                    when(event) {
                        SignUpViewModel.EVENT_ON_CLICK_NEXT -> {
                            val intent = Intent(this@SignUpActivity, SignUpDetailActivity::class.java)
                            intent.putExtra("id", id.value)
                            intent.putExtra("pw", pw.value)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}