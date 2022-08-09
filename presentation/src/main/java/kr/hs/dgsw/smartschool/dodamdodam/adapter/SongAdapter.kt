package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemTodaySongBinding
import kr.hs.dgsw.smartschool.domain.model.song.Video

class SongAdapter : ListAdapter<Video, SongAdapter.TodaySongViewHolder>(SongDiffUtilCallback) {

    class TodaySongViewHolder(val binding: ItemTodaySongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Video) {
            Glide.with(binding.ivTodaySong)
                .load(item.thumbnail)
                .centerCrop()
                .error(R.drawable.default_img)
                .into(binding.ivTodaySong)

            binding.song = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaySongViewHolder {
        return TodaySongViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_today_song,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodaySongViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}