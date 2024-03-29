package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.lostfound.CommentInfo

object LostFoundCommentDiffUtilCallback : DiffUtil.ItemCallback<CommentInfo>() {
    override fun areItemsTheSame(oldItem: CommentInfo, newItem: CommentInfo): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: CommentInfo, newItem: CommentInfo): Boolean = oldItem == newItem
}
