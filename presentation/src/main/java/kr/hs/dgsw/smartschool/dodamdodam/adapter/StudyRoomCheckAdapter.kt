package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.StudyRoomDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemStudyRoomCheckBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.studyroom.StudyRoom
import java.util.*

class StudyRoomCheckAdapter(val onClickStudyRoomCard: (Int) -> Unit) : BaseListAdapter<StudyRoom, ItemStudyRoomCheckBinding>(
    R.layout.item_study_room_check,
    StudyRoomDiffUtilCallback
) {

    override fun action(item: StudyRoom, binding: ItemStudyRoomCheckBinding) {
        val start = item.timeTable?.startTime?.dropLast(3)
        val currentTime = Date().timeFormat()

        binding.tvLocation.text = item.place?.name ?: if (start!! >= currentTime) "미신청" else "시간대가 지났습니다"
        binding.tvTime.text = "${item.timeTable?.startTime} ~ ${item.timeTable?.endTime}"

        binding.tvTimeTable.text = item.timeTable!!.name

        binding.root.setOnClickListener {
            // onOpenLocationApply.value = item.timeTableIdx ?: 0
            onClickStudyRoomCard(item.timeTable!!.id)
        }
    }

    class StudyRoomCheckDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            val index = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex

            if (index == 0 || index == 2) {
                outRect.right = 10
                outRect.top = 10
                outRect.bottom = 10
            } else {
                outRect.left = 10
                outRect.top = 10
                outRect.bottom = 10
            }

        }
    }
}
