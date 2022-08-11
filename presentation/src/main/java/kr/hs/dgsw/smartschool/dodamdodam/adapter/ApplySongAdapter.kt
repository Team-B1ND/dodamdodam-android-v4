package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemApplySongBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.song.Video

class ApplySongAdapter(private val action: (url: String) -> Unit) : ListAdapter<Video, ApplySongAdapter.TodaySongViewHolder>(SongDiffUtilCallback) {

    inner class TodaySongViewHolder(val binding: ItemApplySongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Video) {
            Glide.with(binding.ivSong)
                .load(item.thumbnail)
                .centerCrop()
                .error(R.drawable.default_img)
                .into(binding.ivSong)

            binding.tvApplySongDate.text = item.submitDate?.yearDateFormat()
            binding.song = item
            binding.root.setOnClickListener {
                action.invoke(item.videoUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaySongViewHolder {
        return TodaySongViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_apply_song,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodaySongViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}