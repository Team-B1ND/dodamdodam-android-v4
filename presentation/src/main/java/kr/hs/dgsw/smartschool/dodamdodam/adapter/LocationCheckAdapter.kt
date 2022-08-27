package kr.hs.dgsw.smartschool.dodamdodam.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.StudyRoomDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLocationCheckBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import java.util.Date

class LocationCheckAdapter(val onClickLocationCard: (Int) -> Unit) : BaseListAdapter<Location, ItemLocationCheckBinding>(
    R.layout.item_location_check,
    StudyRoomDiffUtilCallback
) {
    override fun action(item: Location, binding: ItemLocationCheckBinding) {
        val start = "${item.time?.startTime?.dropLast(3)}"
        val end = "${item.time?.endTime?.dropLast(3)}"

        val currentTime = Date().timeFormat()

        binding.tvLocation.text = item.place?.name ?: if (start >= currentTime) "미신청" else "시간대가 지났습니다"
        binding.tvTime.text = "$start ~ $end"

        binding.tvTimeTable.text = item.time?.name

        binding.root.setOnClickListener {
            // onOpenLocationApply.value = item.timeTableIdx ?: 0
            onClickLocationCard(item.timeTableIdx ?: 0)
        }
    }
}
