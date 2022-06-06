package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.StudyRoomDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemStudyRoomBinding
import kr.hs.dgsw.smartschool.domain.model.location.Location

class StudyRoomAdapter : ListAdapter<Location, StudyRoomAdapter.StudyRoomViewHolder>(StudyRoomDiffUtilCallback) {

    class StudyRoomViewHolder(
        private val binding: ItemStudyRoomBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Location) {
            binding.tvLocation.text = item.place
            binding.tvTime.text = item.time

            binding.tvTimeTable.text = "N교시"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyRoomViewHolder {
        return StudyRoomViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_study_room,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudyRoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}