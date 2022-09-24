package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.LostFoundCommentAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundCommentBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import java.time.LocalDate

@AndroidEntryPoint
class LostFoundCommentFragment : BaseFragment<FragmentLostFoundCommentBinding, LostFoundCommentViewModel>(),LostFoundCommentAdapter.CommentCallBack {
    private lateinit var lostFoundAdapter : LostFoundCommentAdapter
    override val viewModel: LostFoundCommentViewModel by viewModels()

    val args : LostFoundCommentFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        lostFoundAdapter = LostFoundCommentAdapter(requireContext())
        mBinding.rvComment.adapter = lostFoundAdapter
    }
    override fun observerViewModel() {
        mBinding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }

        mBinding.btnSendComment.setOnClickListener {
            viewModel.addComment(args.id)
        }


        with(viewModel){
            lifecycleScope.launchWhenStarted {
                getCommentState.collect { state ->
                    if (state.list.isNotEmpty()) {
                        val list = setCommentInfo(state.list)
                        setRecyclerView(list)
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }
    private fun setCommentInfo(lostFoundList: List<Comment>): List<CommentInfo> {
        Log.d("LostFoundCommentFragment",lostFoundList.toString())

        val date = LocalDate.now()
        //viewModel.getComment()
        val list: MutableList<CommentInfo> = mutableListOf()
        lostFoundList.forEach {
            list.add(
                CommentInfo(
                    idx = it.idx,
                    img = it.member.profileImage ?: "",
                    name = it.member.name,
                    uploadTime = date.toString(),
                    content = it.comment
                )
            )
        }
        return list.toList()
    }
    private fun setRecyclerView(list : List<CommentInfo>){
        lostFoundAdapter.submitList(list)
    }

    override fun addComment(comment : String, idx : Int){
        viewModel.addComment(idx)
    }
    override fun deleteComment(idx : Int){
        viewModel.deleteComment(idx)
    }
    override fun modifyComment(comment : String, idx : Int){
        viewModel.modifyComment(comment,idx)
    }
    private fun getComment(idx : Int){
        viewModel.getComment(idx)
    }
}
