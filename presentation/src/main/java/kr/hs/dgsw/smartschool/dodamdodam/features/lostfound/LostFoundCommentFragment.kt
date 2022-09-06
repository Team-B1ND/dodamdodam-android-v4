package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundCommentBinding

class LostFoundCommentFragment : BaseFragment<FragmentLostFoundCommentBinding, LostFoundCommentViewModel>() {
    override val viewModel: LostFoundCommentViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }


    }
}
