package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.StudyRoomDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLocationCheckBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.location.Location
import java.util.Date

class LocationCheckAdapter(val onClickLocationCard: (Int) -> Unit) : ListAdapter<Location, LocationCheckAdapter.LocationCheckViewHolder>(StudyRoomDiffUtilCallback) {

    inner class LocationCheckViewHolder(
        private val binding: ItemLocationCheckBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Location) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationCheckViewHolder {
        return LocationCheckViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_location_check,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationCheckViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
