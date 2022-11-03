package kr.hs.dgsw.smartschool.dodamdodam.widget

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getParentActivity
import java.text.SimpleDateFormat
import java.util.Date

@BindingAdapter("cardViewChecked")
fun setCardViewChecked(view: MaterialCardView, check: MutableLiveData<Boolean>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    check.observe(parentActivity, Observer { value -> view.isChecked = value ?: false })
}

@BindingAdapter("checkBoxChecked")
fun setMutableChecked(view: CheckBox, check: MutableLiveData<Boolean>) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    check.observe(parentActivity, Observer { value -> view.isChecked = value ?: false })
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    visibility?.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    text?.observe(parentActivity, Observer { value -> view.text = value ?: "" })
}

@BindingAdapter("mutableDateText")
fun setMutableDateText(view: TextView, text: MutableLiveData<Date>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("yyyy-MM-dd  E")

    text?.observe(parentActivity, Observer { value -> view.text = format.format(value) ?: "" })
}

@BindingAdapter("mutableImageDrawable")
fun setMutableImageDrawable(view: ImageView, resid: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    if (resid != null) {
        resid.observe(parentActivity, Observer { value -> view.setImageResource(value) })
    } else {
        Glide.with(view.context)
            .load(R.drawable.default_user)
            .into(view)
    }
}

@BindingAdapter("mutableImageUrls")
fun setMutableImageUrls(view: DodamImageViewLayout, url: MutableLiveData<List<String>>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    url?.observe(
        parentActivity,
        Observer { values ->
            if (values == null) return@Observer
            view.putImages(*values.toTypedArray())
        }
    )
}

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    if (url != null) {
        url.observe(
            parentActivity,
            Observer { value ->
                Glide.with(view.context)
                    .load(value)
                    .error(R.drawable.default_user)
                    .into(view)
            }
        )
    } else {
        Glide.with(view.context)
            .load(R.drawable.default_user)
            .into(view)
    }
}

@BindingAdapter("mutableImageUri")
fun setMutableImageUri(view: ImageView, uri: MutableLiveData<Uri>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    if (uri != null) {
        uri.observe(
            parentActivity,
            Observer { value ->
                Glide.with(view.context)
                    .load(value)
                    .error(R.drawable.default_user)
                    .into(view)
            }
        )
    } else {
        Glide.with(view.context)
            .load(R.drawable.default_user)
            .into(view)
    }
}

@BindingAdapter("isSelected")
fun setSelected(view: View, isSelected: Boolean) {
    view.isSelected = isSelected
}

@BindingAdapter("visibility")
fun setMutableVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, image: String?) {
    Glide.with(view.context)
        .load(image ?: return)
        .error(R.drawable.default_img)
        .centerCrop()
        .into(view)
}
