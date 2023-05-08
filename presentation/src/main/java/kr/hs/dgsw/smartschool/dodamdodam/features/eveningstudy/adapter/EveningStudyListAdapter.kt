package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemEveningStudyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.adapter.callback.EveningStudyItemDiffUtil
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyStatus

class EveningStudyListAdapter(
    private val action: EveningStudyAction
) : BaseListAdapter<EveningStudyItem, ItemEveningStudyBinding>(R.layout.item_evening_study, EveningStudyItemDiffUtil) {

    override fun action(item: EveningStudyItem, binding: ItemEveningStudyBinding) {
        val resources = binding.root.resources
        val icon = when (item.allowCheck) {
            EveningStudyStatus.DENIED -> {
                binding.tvEveningStudyStatus.text = "거절됨"
                resources.getDrawable(R.drawable.ic_out_refuse, null)
            }
            EveningStudyStatus.PENDING -> {
                binding.tvEveningStudyStatus.text = "대기중"
                resources.getDrawable(R.drawable.ic_out_unknown, null)
            }
            EveningStudyStatus.ALLOWED -> {
                binding.tvEveningStudyStatus.text = "수락됨"
                resources.getDrawable(R.drawable.ic_out_ok, null)
            }
            else -> return
        }

        Glide.with(binding.root)
            .load(icon)
            .into(binding.ivEveningStudyStatus)
        binding.tvEveningStudyReason.text = item.reason

        binding.tvLabelDateStart.text = "시작 날짜"
        binding.tvLabelDateEnd.text = "종료 날짜"

        binding.tvDateStart.text = item.startAt.yearDateFormat()
        binding.tvDateEnd.text = item.endAt.yearDateFormat()

        binding.btnDelete.setOnClickListener {
            action.onClickDelete(item.id)
        }

        binding.cardBase.setOnClickListener {
            action.onClickItem(item.id)
        }
    }

    interface EveningStudyAction {
        fun onClickDelete(id: Int)
        fun onClickItem(id: Int)
    }
}
