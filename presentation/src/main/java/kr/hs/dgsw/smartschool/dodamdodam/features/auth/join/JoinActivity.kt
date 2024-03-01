package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join

import android.content.Intent
import androidx.activity.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityJoinBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.detail.JoinDetailActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.removeBlankInString
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.util.Utils

class JoinActivity : BaseActivity<ActivityJoinBinding, JoinViewModel>() {
    override val viewModel: JoinViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            shortToast("승인을 기다려주세요!")
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
        intent.putExtra("pw", Utils.encryptSHA512((viewModel.pw.value?:"").removeBlankInString()))
        startActivity(intent)
    }
}
