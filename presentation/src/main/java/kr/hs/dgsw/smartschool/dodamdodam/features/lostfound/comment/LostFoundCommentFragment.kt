package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundCommentBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.LostFoundCommentAdapter
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment.update.UpdateCommentDialog
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo
import java.time.LocalDate

@AndroidEntryPoint
class LostFoundCommentFragment : BaseFragment<FragmentLostFoundCommentBinding, LostFoundCommentViewModel>(), LostFoundCommentAdapter.CommentCallBack, UpdateCommentDialog.CommentCallBack {
    private lateinit var lostFoundAdapter: LostFoundCommentAdapter
    override val viewModel: LostFoundCommentViewModel by viewModels()

    private val args: LostFoundCommentFragmentArgs by navArgs()

    var myId: String = ""

    override fun onStart() {
        super.onStart()
        viewModel.getMyInfo()
        lostFoundAdapter = LostFoundCommentAdapter(requireContext(), this)
        mBinding.rvComment.adapter = lostFoundAdapter
        viewModel.lostFoundId = args.id
        viewModel.getComment(args.id)
    }
    override fun onResume() {
        super.onResume()
        viewModel.getMyInfo()
    }
    override fun observerViewModel() {
        with(mBinding) {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            swipeRefresh.setOnRefreshListener {
                viewModel.getComment(args.id)
                swipeRefresh.isRefreshing = false
            }
            btnSendComment.setOnClickListener {
                viewModel.addComment(args.id)
                etComment.clearFocus()
                etComment.setText("")
            }
        }

        with(viewModel) {
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
            lifecycleScope.launchWhenStarted {
                getMyInfoState.collect { state ->
                    if (state.myId.isNotEmpty()) {
                        myId = state.myId
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }
    private fun setCommentInfo(lostFoundList: List<Comment>): List<CommentInfo> {
        Log.d("LostFoundCommentFragment", lostFoundList.toString())

        val date = LocalDate.now()
        // viewModel.getComment()
        val list: MutableList<CommentInfo> = mutableListOf()
        lostFoundList.forEach {
            list.add(
                CommentInfo(
                    idx = it.idx,
                    img = it.member.profileImage ?: "",
                    name = it.member.name,
                    uploadTime = date.toString(),
                    content = it.comment,
                    isMine = ((it.member.id == myId))
                )
            )
        }
        return list.toList()
    }
    private fun setRecyclerView(list: List<CommentInfo>) {
        lostFoundAdapter.submitList(list)
    }
    override fun deleteComment(idx: Int) {
        viewModel.deleteComment(idx)
    }
    override fun openDialog(idx: Int) {
        val dialog = UpdateCommentDialog(viewModel.comment.value ?: "", idx, this)
        // 알림창이 띄워져있는 동안 배경 클릭 막기
        dialog.isCancelable = false
        dialog.show(childFragmentManager, "UpdateCommentDialog")
    }

    override fun modifyComment(idx: Int, newComment: String) {
        Log.e("LostFoundCommentFragment", "실행$idx$newComment")
        viewModel.modifyComment(idx, newComment)
    }
}
