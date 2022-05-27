package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.SignUpPagerAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseActivity
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpBinding


@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {
    override val viewModel: SignUpViewModel by viewModels()
    val signUpPagerAdapter =  SignUpPagerAdapter(this)
    override fun observerViewModel() {
        with(mBinding){
            viewPager.adapter = signUpPagerAdapter
        }
    }
}