package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.activity.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.SignUpPagerAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpBinding


class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()



    override fun observerViewModel() {

    }
}