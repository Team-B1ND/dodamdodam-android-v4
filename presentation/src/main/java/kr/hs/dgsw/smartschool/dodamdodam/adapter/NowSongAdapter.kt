package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.SongDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemNowSongBinding
import kr.hs.dgsw.smartschool.domain.model.song.Song

class NowSongAdapter : ListAdapter<Song, NowSongAdapter.NowSongViewHolder>(SongDiffUtilCallback) {

    class NowSongViewHolder(val binding: ItemNowSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Song) {
            binding.song = item

            Glide.with(binding.root)
                .load(item.songImage)
                .error(R.drawable.default_img)
                .centerCrop()
                .into(binding.ivNowSong)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowSongViewHolder {
        return NowSongViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_now_song,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NowSongViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}