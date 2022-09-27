package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.comment.update

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
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
class ConfirmDialog(
    confirmDialogInterface: ConfirmDialogInterface,
    text: String, id: Int
) : DialogFragment() {

    private var confirmDialogInterface: ConfirmDialogInterface? = null
    private lateinit var binding :
    private var text: String? = null
    private var id: Int? = null

    init {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogPackageDeleteBinding.inflate(inflater, container, false)
        val view = binding.root

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.confirmTextView.text = text

        // 취소 버튼 클릭
        binding.noButton.setOnClickListener {
            dismiss()
        }

        // 확인 버튼 클릭
        binding.yesButton.setOnClickListener {
            this.confirmDialogInterface?.onYesButtonClick(id!!)
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface ConfirmDialogInterface {
    fun onYesButtonClick(id: Int)
}
