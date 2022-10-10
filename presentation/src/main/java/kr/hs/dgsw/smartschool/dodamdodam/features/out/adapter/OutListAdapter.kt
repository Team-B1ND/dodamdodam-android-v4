package kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter

import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemOutBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter.callback.OutItemDiffUtil
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.model.out.OutItem

class OutListAdapter(
    private val onClickDelete: (state: Int, idx: Int) -> Unit
) : BaseListAdapter<OutItem, ItemOutBinding>(R.layout.item_out, OutItemDiffUtil) {

    override fun action(item: OutItem, binding: ItemOutBinding) {
        val resources = binding.root.resources
        val icon = when (item.status) {
            OutStatus.DENIED -> {
                binding.tvOutStatus.text = "거절됨"
                resources.getDrawable(R.drawable.ic_out_refuse, null)
            }
            OutStatus.PENDING -> {
                binding.tvOutStatus.text = "대기중"
                resources.getDrawable(R.drawable.ic_out_unknown, null)
            }
            OutStatus.ALLOWED -> {
                binding.tvOutStatus.text = "수락됨"
                resources.getDrawable(R.drawable.ic_out_ok, null)
            }
            else -> return
        }

        Glide.with(binding.root)
            .load(icon)
            .into(binding.ivOutStatus)

        binding.tvOutType.text = if (item.isOutSleeping()) "외박" else "외출"
        binding.tvOutReason.text = item.reason

        if (item is OutGoing) {
            binding.tvLabelDate.text = "외출 날짜"
            binding.tvLabelTime.text = "외출 시간"
            binding.layoutDateEnd.visibility = View.INVISIBLE

            binding.tvDate.text = item.startTime.yearDateFormat()
            binding.tvTime.text = item.startTime.timeFormat()
            binding.tvTimeEnd.text = item.endTime.timeFormat()
        } else {
            binding.tvLabelDate.text = "외박 날짜"
            binding.tvLabelTime.text = "외박 시간"
            binding.layoutDateEnd.visibility = View.VISIBLE

            binding.tvDate.text = item.startTime.yearDateFormat()
            binding.tvDateEnd.text = item.endTime.yearDateFormat()
            binding.tvTime.text = item.startTime.timeFormat()
            binding.tvTimeEnd.text = item.endTime.timeFormat()
        }

        binding.btnDelete.setOnClickListener {
            onClickDelete.invoke(if (item is OutGoing) 0 else 1, item.idx)
        }
    }
}
