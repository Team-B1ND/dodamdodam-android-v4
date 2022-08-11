package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.MelonChartDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemApplySongBinding
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemRecommendSongBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.song.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.Video

class RecommendSongAdapter : ListAdapter<MelonChart, RecommendSongAdapter.RecommendSongViewHolder>(MelonChartDiffUtilCallback) {

    class RecommendSongViewHolder(val binding: ItemRecommendSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MelonChart) {
            Glide.with(binding.ivRecommendSong)
                .load(item.thumbnail)
                .centerCrop()
                .error(R.drawable.default_img)
                .into(binding.ivRecommendSong)

            binding.tvRecommendSongTitle.text = item.title
            binding.tvRank.text = item.rank + "위"
            binding.tvRecommendSongArtist.text = item.artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendSongViewHolder {
        return RecommendSongViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recommend_song,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecommendSongViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}