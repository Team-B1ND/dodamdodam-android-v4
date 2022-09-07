package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundCommentBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

class LostFoundCommentFragment : BaseFragment<FragmentLostFoundCommentBinding, LostFoundCommentViewModel>() {
    override val viewModel: LostFoundCommentViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }


        with(viewModel){
            lifecycleScope.launchWhenStarted {
                getCommentState.collect { state ->
                    if (state.list.isNotEmpty()) {
                       // val list = setLostInfo(state.list)
                        //setRecyclerView(list)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }
}
