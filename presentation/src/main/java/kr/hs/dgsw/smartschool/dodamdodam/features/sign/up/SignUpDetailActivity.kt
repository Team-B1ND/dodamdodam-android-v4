package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_up_detail.*
import kotlinx.android.synthetic.main.item_loading_progressbar.*
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpDetailBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

@AndroidEntryPoint
class SignUpDetailActivity : BaseActivity<ActivitySignUpDetailBinding, SignUpDetailViewModel>() {
    override val viewModel: SignUpDetailViewModel by viewModels()

    override fun observerViewModel() {
        collectSignUpState()
        setLinkedTextView()

        val intent = Intent()
        viewModel.id = intent.getStringExtra("id") ?: ""
        viewModel.pw = intent.getStringExtra("pw") ?: ""

        mBinding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun collectSignUpState() {
        lifecycleScope.launchWhenStarted {
            viewModel.signUpState.collect { state ->
                if (state.result.isNotEmpty()) {
                    shortToast("회원가입에 성공하였습니다.")
                    startActivityWithFinishAll(SignInActivity::class.java)
                }

                if (state.isLoading) {
                    isStateLoading(true)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    isStateLoading(false)
                }
            }
        }
    }

    private fun setLinkedTextView() {
        // textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        mBinding.tvServicePolicy.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        mBinding.tvPersonalInfo.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        tv_service_policy.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(resources.getString(R.string.link_service_policy))
            startActivity(intent)
        }

        tv_personal_info.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(resources.getString(R.string.link_personal_info))
            startActivity(intent)
        }
    }

    private fun isStateLoading(state: Boolean) {
        mBinding.scrollView.isVisible = !state
        mBinding.btnBack.isEnabled = !state
        mBinding.btnSignUp.isEnabled = !state
    }

    override fun bindingViewEvent() {}
}