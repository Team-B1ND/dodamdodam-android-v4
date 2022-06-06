package kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signin

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivityAutoSignInBinding

@AndroidEntryPoint
class AutoSignInActivity : BaseActivity<ActivityAutoSignInBinding, AutoSignInViewModel>() {
    override val viewModel: AutoSignInViewModel by viewModels()
    override fun observerViewModel() {
        viewModel.autoSignIn()
    }
}