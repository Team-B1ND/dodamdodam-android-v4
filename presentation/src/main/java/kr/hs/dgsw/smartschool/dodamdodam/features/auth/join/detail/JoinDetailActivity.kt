package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.detail

import android.graphics.Paint
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityJoinDetailBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.LoginActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openUrlWithBrowser
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

@AndroidEntryPoint
class JoinDetailActivity : BaseActivity<ActivityJoinDetailBinding, JoinDetailViewModel>() {
    override val viewModel: JoinDetailViewModel by viewModels()

    override fun observerViewModel() {
        collectSignUpState()
        mBinding.tvServicePolicy.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        mBinding.tvPersonalInfo.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        setLinkedTextView(mBinding.tvServicePolicy, mBinding.tvPersonalInfo)
        initViewEvent()

        val intent = intent
        viewModel.id = intent.getStringExtra("id") ?: ""
        viewModel.pw = intent.getStringExtra("pw") ?: ""

        mBinding.btnBack.setOnClickListener {
            finish()
        }

        mBinding.checkboxAgree.setOnClickListener {
            viewModel.isAgree.value = mBinding.checkboxAgree.isChecked
        }
    }

    private fun collectSignUpState() {
        lifecycleScope.launchWhenStarted {
            viewModel.joinState.collect { state ->
                if (state.result.isNotEmpty()) {
                    shortToast("승인을 기다려 주세요")
                    startActivityWithFinishAll(LoginActivity::class.java)
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

    private fun setLinkedTextView(servicePolicy: View, personalInfo: View) {
        // textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        servicePolicy.setOnClickListener {
            this.openUrlWithBrowser(resources.getString(R.string.link_service_policy))
        }

        personalInfo.setOnClickListener {
            this.openUrlWithBrowser(resources.getString(R.string.link_personal_info))
        }
    }

    private fun isStateLoading(state: Boolean) {
        mBinding.btnBack.isEnabled = !state
        mBinding.btnJoin.isEnabled = !state
    }

    private fun initViewEvent() {
        bindingViewEvent { event ->
            when (event) {
                JoinDetailViewModel.EVENT_EMPTY -> shortToast("입력란을 모두 채워 주세요.")
                JoinDetailViewModel.EVENT_NOT_PHONE_NUMBER -> shortToast("전화번호 형식이 일치하지 않습니다.")
                JoinDetailViewModel.EVENT_NOT_AGREE -> shortToast("방침에 동의해 주세요.")
                JoinDetailViewModel.EVENT_NOT_MATCH_FORM -> shortToast("형식이 일치하지 않습니다.")
                JoinDetailViewModel.EVENT_NOT_EMAIL -> shortToast("이메일 형식이 일치하지 않습니다.")
            }
        }
    }
}
