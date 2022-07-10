package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_up_detail.*
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpDetailBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInViewModel

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

    override fun bindingViewEvent() {}
}