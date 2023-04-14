package kr.hs.dgsw.smartschool.dodamdodam.features.dauth

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityDauthBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.JoinActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivity
import kr.hs.dgsw.smartschool.domain.util.Utils.encryptSHA512

@AndroidEntryPoint
class DAuthActivity : BaseActivity<ActivityDauthBinding, DAuthViewModel>() {
    override val viewModel: DAuthViewModel by viewModels()

    override fun observerViewModel() = with(mViewModel) {

        bindingViewEvent { event ->
            when (event) {
                DAuthViewModel.EVENT_ON_CLICK_JOIN -> startActivity(JoinActivity::class.java)
                DAuthViewModel.EVENT_ON_CLICK_LOGIN -> {
                    val intent = Intent()
                    intent.putExtra("id", id.value)
                    intent.putExtra("pw", encryptSHA512(pw.value!!))
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}
