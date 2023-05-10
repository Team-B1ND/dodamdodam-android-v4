package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.adapter

import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemNightStudyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.adapter.callback.NightStudyItemDiffUtil
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyItem
import kr.hs.dgsw.smartschool.domain.model.nightstudy.NightStudyStatus

class NightStudyListAdapter(
    private val action: NightStudyAction
) : BaseListAdapter<NightStudyItem, ItemNightStudyBinding>(R.layout.item_night_study, NightStudyItemDiffUtil) {

    override fun action(item: NightStudyItem, binding: ItemNightStudyBinding) {
        val resources = binding.root.resources
        val icon = when (item.allowCheck) {
            NightStudyStatus.DENIED -> {
                binding.tvNightStudyStatus.text = "거절됨"
                resources.getDrawable(R.drawable.ic_out_refuse, null)
            }
            NightStudyStatus.PENDING -> {
                binding.tvNightStudyStatus.text = "대기중"
                resources.getDrawable(R.drawable.ic_out_unknown, null)
            }
            NightStudyStatus.ALLOWED -> {
                binding.tvNightStudyStatus.text = "수락됨"
                resources.getDrawable(R.drawable.ic_out_ok, null)
            }
            else -> return
        }

        val isNeedPhone = when (item.isPhone) {
            true -> {
                resources.getDrawable(R.drawable.ic_out_ok, null)
            }
            false -> {
                resources.getDrawable(R.drawable.ic_out_refuse, null)
            }
        }

        Glide.with(binding.root)
            .load(icon)
            .into(binding.ivNightStudyStatus)
        Glide.with(binding.root)
            .load(isNeedPhone)
            .into(binding.ivNightStudyIsPhoneStatus)
        binding.tvContent.text = "학습내용"
        binding.tvNightStudyReason.text = item.content
        binding.tvIsNeedPhone.text = "휴대폰 사용 여부"
        binding.tvNightStudyTitle.text = "심자"
        binding.tvLabelDateStart.text = "시작 날짜"
        binding.tvLabelDateEnd.text = "종료 날짜"

        binding.tvDateStart.text = item.startAt.yearDateFormat()
        binding.tvDateEnd.text = item.endAt.yearDateFormat()

        binding.btnDelete.setOnClickListener {

            action.onClickDelete(item.id)
        }
    }

    interface NightStudyAction {
        fun onClickDelete(id: Int)
    }
}
