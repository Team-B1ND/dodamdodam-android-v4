package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpDetailBinding

@AndroidEntryPoint
class SignUpDetailActivity : BaseActivity<ActivitySignUpDetailBinding, SignUpDetailViewModel>() {
    override val viewModel: SignUpDetailViewModel by viewModels()

    override fun observerViewModel() {

    }
}