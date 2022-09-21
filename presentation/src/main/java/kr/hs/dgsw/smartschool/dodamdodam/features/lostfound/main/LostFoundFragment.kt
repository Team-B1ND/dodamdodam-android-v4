package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kr.hs.dgsw.smartschool.domain.model.member.Member

@AndroidEntryPoint
class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    private lateinit var lostFoundAdapter : LostFoundAdapter
    override val viewModel: LostFoundViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getLostFoundList(1,"LOST")
        return mBinding.root
    }

    override fun observerViewModel() {
        lostFoundAdapter = LostFoundAdapter(requireContext(), this)

        mBinding.btnBack.setOnClickListener {
            this.findNavController().popBackStack()
        }
        mBinding.fbAddLostAndFound.setOnClickListener{
            this.findNavController().navigate(R.id.action_lostFoundFragment_to_lostFoundWriteFragment)
        }
        mBinding.btnSearch.setOnClickListener{
            viewModel.searchLostFound()
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
        val action = LostFoundFragmentDirections.actionLostFoundFragmentToLostFoundUpdateFragment(idx)
        findNavController().navigate(action)
    }



    override fun deleteLostFound(idx: Int) {
        viewModel.deleteLostFound(idx)
    }

    private fun setLostInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        Log.d("LostFoundFragment","setRecyclerView()")
        Log.d("LostFoundFragment",lostFoundList.toString())
        viewModel.getLostFoundList(1,if(mBinding.lostFoundSpinner.selectedItemPosition == 0) "LOST" else "FOUND")
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
                    place = it.place,
                    member = it.member
                )
            )
        }
        Log.d("LostFoundFragment",list.toList().toString())
        return list.toList()
    }
    private fun setRecyclerView(list : List<LostInfo>){
        Log.d("LostFoundFragment","setRecyclerView()")
        mBinding.rvLostFound.adapter = lostFoundAdapter
        lostFoundAdapter.submitList(list)
    }
}
