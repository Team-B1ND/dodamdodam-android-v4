package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

@AndroidEntryPoint
class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    override val viewModel: LostFoundViewModel by viewModels()
    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getLostFoundState.collect { state ->
                    if (state.list.isNotEmpty()) {
                        val list = setLostInfo(state.list)
                        setRecyclerView(list)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    override fun openComment(idx: Int) {
        findNavController().navigate(R.id.action_lostFoundFragment_to_lostFoundCommentFragment)
    }

    private fun setLostInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        viewModel.getLostFoundList(1,mBinding.lostFoundSpinner.selectedItemPosition)
        Log.d("LostFoundFragment", "실행")
        val list: MutableList<LostInfo> = mutableListOf()
        // val today = LocalDate.now()
        lostFoundList.forEach {
            list.add(
                LostInfo(
                    idx = it.idx,
                    img = it.profileImage,
                    name = it.name ?: "작성자 미상",
                    uploadTime = it.uploadTime.toString(),
                    title = it.title,
                    content = it.content,
                    place = it.place ?: ""
                )
            )
        }
        return list.toList()
    }
    private fun setRecyclerView(list : List<LostInfo>){
        val lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostAndFound.adapter = lostFoundAdapter
        lostFoundAdapter.submitList(list)
    }
}
