package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.write

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundWriteBinding

class LostFoundWriteFragment : BaseFragment<FragmentLostFoundWriteBinding, LostFoundWriteViewModel>() {
    override val viewModel: LostFoundWriteViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener{
            this.findNavController().popBackStack()
        }
        mBinding.btnImageAdd.setOnClickListener {
        }
        mBinding.btnImageDelete.setOnClickListener {
        }
        with(viewModel){

        }
    }
}
