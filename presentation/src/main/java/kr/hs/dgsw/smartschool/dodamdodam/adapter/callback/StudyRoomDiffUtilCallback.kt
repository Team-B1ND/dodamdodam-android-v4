package kr.hs.dgsw.smartschool.dodamdodam.adapter.callback

import androidx.recyclerview.widget.DiffUtil
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom

object StudyRoomDiffUtilCallback : DiffUtil.ItemCallback<StudyRoom>() {
    override fun areItemsTheSame(oldItem: StudyRoom, newItem: StudyRoom) = oldItem == newItem
    override fun areContentsTheSame(oldItem: StudyRoom, newItem: StudyRoom) = oldItem == newItem
}
