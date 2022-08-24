package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join

import android.content.Intent
import androidx.activity.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.removeBlankInString
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

class JoinActivity : BaseActivity<ActivitySignUpBinding, JoinViewModel>() {
    override val viewModel: JoinViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            finish()
        }

        bindingViewEvent { event ->
            when (event) {
                JoinViewModel.EVENT_ON_CLICK_NEXT -> startSingUpDetailActivity()
                JoinViewModel.EVENT_EMPTY -> shortToast("입력란을 모두 채워 주세요.")
                JoinViewModel.EVENT_NOT_SAME_PW -> shortToast("비밀번호가 일치하지 않습니다.")
                JoinViewModel.EVENT_NOT_MATCH_FORM -> shortToast("형식이 일치하지 않습니다.")
            }
        }
    }

    private fun startSingUpDetailActivity() {
        val intent = Intent(this@JoinActivity, JoinDetailActivity::class.java)
        intent.putExtra("id", viewModel.id.value?.removeBlankInString())
        intent.putExtra("pw", viewModel.pw.value?.removeBlankInString())
        startActivity(intent)
    }
}
