package kr.hs.dgsw.smartschool.dodamdodam.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R

class DodamImageView : androidx.appcompat.widget.AppCompatImageView {

    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null

    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr
    }

    fun putImage(url: String) {
        mContext?.let {
            Glide.with(it)
                .load(url)
                .fitCenter()
                .error(R.color.color_soft_gray)
                .into(this)
        }
    }

    fun putImage(uri: Uri) {
        mContext?.let {
            Glide.with(it)
                .load(uri)
                .fitCenter()
                .error(R.color.color_soft_gray)
                .into(this)
        }
    }

    fun putImage(drawable: Drawable) {
        mContext?.let {
            Glide.with(it)
                .load(drawable)
                .fitCenter()
                .error(R.color.color_soft_gray)
                .into(this)
        }
    }
}
