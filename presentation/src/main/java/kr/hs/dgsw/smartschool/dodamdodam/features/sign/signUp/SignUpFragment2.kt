package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSignUp2Binding

@AndroidEntryPoint
class SignUpFragment2 : BaseFragment<ItemSignUp2Binding, SignUpViewModel>(){

    override val viewModel: SignUpViewModel by viewModels()

    override fun observerViewModel() {
    }
}