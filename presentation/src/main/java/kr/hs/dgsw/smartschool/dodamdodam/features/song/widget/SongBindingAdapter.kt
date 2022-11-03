package kr.hs.dgsw.smartschool.dodamdodam.features.song.widget

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat

@BindingAdapter("songThumbnail")
fun setSongThumbnail(view: ImageView, thumbnailUrl: String) {
    Glide.with(view.context)
        .load(thumbnailUrl)
        .centerCrop()
        .error(R.drawable.default_img)
        .into(view)
}

@BindingAdapter("songApplyDate")
fun setSongDate(view: TextView, date: String) {
    view.text = "신청일: ${date.yearDateFormat()}"
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}
