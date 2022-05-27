package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemSignUp2Binding

class SignUpFragment2 : BaseFragment<ItemSignUp2Binding, SignUpViewModel>(){

    override val viewModel: SignUpViewModel by viewModels()

    override fun observerViewModel() {
        TODO("Not yet implemented")
    }
    private fun setSpinner() {
        var yearSpinner : Spinner = mBinding.generationSpinner

        ArrayAdapter.createFromResource(
            this,
            R.array.generation_array,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            yearSpinner.onItemSelectedListener
            yearSpinner.adapter = adapter
            yearSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                    //TODO(ViewModel에 값 넘겨주기)
                    viewModel.generation.value = (pos+1).toString()
                }

            }
        }
    }
}