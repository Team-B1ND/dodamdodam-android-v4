package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R

@BindingAdapter("generation")
fun setGeneration(view: TextView, generation: Int) {
    view.text = "${generation}기"
}

@BindingAdapter("generation_count")
fun setGenerationCount(view: TextView, listSize: Int) {
    view.text = "졸업생: ${listSize}명"
}

@BindingAdapter("githubId")
fun setGithubId(view: TextView, githubId: String) {
    view.text = "github.com/${githubId}"
}

@BindingAdapter("itmap_profile")
fun setItMapProfile(view: ImageView, image: String?) {
    Glide.with(view.context)
        .load(image ?: return)
        .error(R.drawable.default_img)
        .centerCrop()
        .into(view)
}

@BindingAdapter("company_icon")
fun setItMapCompanyIcon(view: TextView, companyName: String?) {
    companyName?.let {
        view.text = it[0].toString()
    }
}