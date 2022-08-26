package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding

class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    override val viewModel: LostFoundViewModel by viewModels()

    override fun observerViewModel() {
        mBinding.rvLostAndFound.adapter = LostFoundAdapter(requireContext(),this)
    }

    override fun openComment(idx: Int) {
        viewModel.openComment(idx)
    }
}
