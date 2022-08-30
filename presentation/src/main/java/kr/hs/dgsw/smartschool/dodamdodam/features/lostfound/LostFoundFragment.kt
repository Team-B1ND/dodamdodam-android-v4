package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

@AndroidEntryPoint
class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    override val viewModel: LostFoundViewModel by viewModels()
    private lateinit var lostFoundAdapter: LostFoundAdapter
    override fun observerViewModel() {
        setRecyclerView()
    }

    override fun openComment(idx: Int) {
        findNavController().navigate(R.id.action_lostFoundFragment_to_lostFoundCommentFragment)
    }

    private fun setLostInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        viewModel.getLostFoundList(4,mBinding.lostFoundSpinner.selectedItemPosition)
        val list: MutableList<LostInfo> = mutableListOf()
        // val today = LocalDate.now()
        lostFoundList.forEach {
            list.add(
                LostInfo(
                    idx = it.idx,
                    img = it.profileImage,
                    name = it.name ?: "작성자 모름",
                    uploadTime = it.uploadTime.toString(),
                    title = it.title,
                    content = it.content,
                    place = it.place ?: ""
                )
            )
        }
        return list.toList()
    }
    private fun setRecyclerView(){
        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostAndFound.adapter = lostFoundAdapter
        lostFoundAdapter.submitList(setLostInfo(viewModel.getLostFoundState.value.list))
    }
}
