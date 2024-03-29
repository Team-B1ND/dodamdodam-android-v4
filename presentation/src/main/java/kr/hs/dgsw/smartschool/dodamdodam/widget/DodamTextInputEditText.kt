package kr.hs.dgsw.smartschool.dodamdodam.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import kr.hs.dgsw.smartschool.dodamdodam.R

class DodamTextInputEditText : TextInputEditText {

    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null

    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr

        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.default_edittext_text))
        this.backgroundTintList = ContextCompat.getColorStateList(context, R.color.color_transparent)
    }
}
