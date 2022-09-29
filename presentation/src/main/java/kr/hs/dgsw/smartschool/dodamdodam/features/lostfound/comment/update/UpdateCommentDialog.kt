package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment.update

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.LostFoundCommentAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.DialogUpdateCommentBinding
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundCommentBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment.LostFoundCommentViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import java.time.LocalDate

@AndroidEntryPoint
class UpdateCommentDialog(
    private val comment : String,
    private val commentId : Int,
    private val listener : CommentCallBack
) : DialogFragment() {
    private lateinit var binding : DialogUpdateCommentBinding

    interface CommentCallBack{
        fun modifyComment(idx : Int, newComment: String)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.dialog_update_comment,container,false)

        binding.etComment.setText(comment)
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCancel.setOnClickListener{
            onDestroyView()
        }
        binding.btnUpdate.setOnClickListener {
            listener.modifyComment(commentId,binding.etComment.text.toString())
            onDestroyView()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}
