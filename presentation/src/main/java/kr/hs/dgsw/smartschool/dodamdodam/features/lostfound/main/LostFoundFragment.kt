package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.main

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment.LostFoundCommentViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

@AndroidEntryPoint
class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    private lateinit var lostFoundAdapter : LostFoundAdapter
    override val viewModel: LostFoundViewModel by viewModels()

    override fun observerViewModel() {
        //TODO 테스트용임 지워야함
        val list = listOf(
            LostInfo(idx = 1, title = "경태를 잃어버렸습니다", content = "특이사항은 탈모입니다."),
            LostInfo(idx = 2, title = "경태를 잃어버렸습니다", content = "특이사항은 탈모입니다."),
            LostInfo(idx = 3, title = "경태를 잃어버렸습니다", content = "특이사항은 탈모입니다.")
        )
        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostFound.adapter = lostFoundAdapter
        lostFoundAdapter.submitList(list)

        mBinding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }
        mBinding.fbAddLostAndFound.setOnClickListener{
            this.findNavController().navigate(R.id.action_lostFoundFragment_to_lostFoundWriteFragment)
        }
        mBinding.btnSearch.setOnClickListener{
            viewModel.searchLostFound()
        }
        mBinding.checkMy.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked) viewModel.myLostFound()
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

    override fun modifyLostFound(idx: Int) {
        findNavController().navigate(R.id.action_lostFoundFragment_to_lostFoundWriteFragment)
    }

    override fun deleteLostFound(idx: Int) {
        viewModel.deleteLostFound(idx)
    }

    private fun setLostInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        Log.d("LostFoundFragment","setRecyclerView()")
        /*Log.d("LostFoundFragment",lostFoundList.toString())
        viewModel.getLostFound(1,if(mBinding.lostFoundSpinner.selectedItemPosition == 0) "LOST" else "FOUND")
        val list: MutableList<LostInfo> = mutableListOf()
        // val today = LocalDate.now()
        lostFoundList.forEach {
            list.add(
                LostInfo(
                    idx = it.idx,
                    img = it.member.profileImage ?: "",
                    name = it.member.name,
                    uploadTime = it.createAt,
                    title = it.title,
                    content = it.content,
                    place = it.place
                )
            )
        }
        Log.d("LostFoundFragment",list.toList().toString())
        return list.toList()*/
        val list = listOf(
            LostInfo(idx = 1, title = "경태를 잃어버렸습니다", content = "특이사항은 탈모입니다."),
            LostInfo(idx = 2, title = "경태를 잃어버렸습니다", content = "특이사항은 탈모입니다."),
            LostInfo(idx = 3, title = "경태를 잃어버렸습니다", content = "특이사항은 탈모입니다.")
        )
        return list
    }
    private fun setRecyclerView(list : List<LostInfo>){
        Log.d("LostFoundFragment","setRecyclerView()")
        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostFound.adapter = lostFoundAdapter
        lostFoundAdapter.submitList(list)
    }
}
