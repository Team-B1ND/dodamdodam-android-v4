package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSignUp1Binding


class SignUpFragment1 : BaseFragment<ItemSignUp1Binding,SignUpViewModel>(){

    override val viewModel: SignUpViewModel by viewModels()

    override fun observerViewModel() {

   }
}