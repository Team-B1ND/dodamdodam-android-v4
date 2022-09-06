package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

object LostFoundCommentDiffUtilCallback : DiffUtil.ItemCallback<CommentInfo>() {
    override fun areItemsTheSame(oldItem: CommentInfo, newItem: CommentInfo): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: CommentInfo, newItem: CommentInfo): Boolean = oldItem == newItem
}
