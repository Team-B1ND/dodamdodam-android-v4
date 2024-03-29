package kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter

import android.view.View
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemOutBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.out.adapter.callback.OutItemDiffUtil
import kr.hs.dgsw.smartschool.dodamdodam.features.out.etc.OutState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus

class OutListAdapter(
    private val action: OutAction
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

        if (!item.isOutSleeping()) {
            binding.tvLabelDate.text = "외출 날짜"
            binding.tvLabelTime.text = "외출 시간"
            binding.layoutDateEnd.visibility = View.INVISIBLE

            binding.tvDate.text = item.startOutDate.yearDateFormat()
            binding.tvTime.text = item.startOutDate.timeFormat()
            binding.tvTimeEnd.text = item.endOutDate.timeFormat()
        } else {
            binding.tvLabelDate.text = "외박 날짜"
            binding.tvLabelTime.text = "외박 시간"
            binding.layoutDateEnd.visibility = View.VISIBLE

            binding.tvDate.text = item.startOutDate.yearDateFormat()
            binding.tvDateEnd.text = item.endOutDate.yearDateFormat()
            binding.tvTime.text = item.startOutDate.timeFormat()
            binding.tvTimeEnd.text = item.endOutDate.timeFormat()
        }

        binding.btnDelete.setOnClickListener {
            action.onClickDelete(if (!item.isOutSleeping()) OutState.OutGoing else OutState.OutSleeping, item.id)
        }

        binding.cardBase.setOnClickListener {
            action.onClickItem(if (!item.isOutSleeping()) OutState.OutGoing else OutState.OutSleeping, item.id)
        }
    }

    interface OutAction {
        fun onClickDelete(state: OutState, id: Int)
        fun onClickItem(state: OutState, id: Int)
    }
}
