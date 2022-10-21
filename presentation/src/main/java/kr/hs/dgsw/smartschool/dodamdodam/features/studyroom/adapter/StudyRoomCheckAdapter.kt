package kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.studyroom.adapter.callback.StudyRoomDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemStudyRoomCheckBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import java.util.Date

class StudyRoomCheckAdapter(val onClickStudyRoomCard: (Int) -> Unit) : BaseListAdapter<StudyRoom, ItemStudyRoomCheckBinding>(
    R.layout.item_study_room_check,
    StudyRoomDiffUtilCallback
) {

    override fun action(item: StudyRoom, binding: ItemStudyRoomCheckBinding) {

        binding.tvLocation.text = item.place?.name ?: if (item.isExpire()) "만료" else "미신청"
        binding.tvTime.text = "${item.timeTable?.startTime} ~ ${item.timeTable?.endTime}"

        binding.tvTimeTable.text = item.timeTable!!.name

        binding.root.setOnClickListener {
            if (item.isExpire().not()){
                onClickStudyRoomCard(item.timeTable!!.id)
            }
        }

    }
}
