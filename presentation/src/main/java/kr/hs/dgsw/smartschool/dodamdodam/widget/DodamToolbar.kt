package kr.hs.dgsw.smartschool.dodamdodam.widget

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNight

class DodamToolbar : Toolbar {
    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context!!, attrs, defStyleAttr)

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        try {
            val superClass: Class<*>? = javaClass.superclass
            if (superClass != null) {
                val field = superClass.getDeclaredField("mTitleTextView")
                field.isAccessible = true
                val textView = field[this] as TextView?
                try {
                    textView?.typeface = ResourcesCompat.getFont(
                        context,
                        R.font.nanum_square_regular
                    )
                    textView?.setTextColor(if (context.isNight()) Color.WHITE else Color.BLACK)
                    textView?.textSize = 18f
                } catch (ignore: NotFoundException) {
                }
                val layoutParams = generateDefaultLayoutParams()
                textView?.layoutParams = layoutParams
            }
        } catch (ignore: NoSuchFieldException) {
        } catch (ignore: IllegalAccessException) {
        }
    }

    override fun setSubtitle(subtitle: CharSequence?) {
        super.setSubtitle(subtitle)
        try {
            val superClass: Class<*>? = javaClass.superclass
            if (superClass != null) {
                val field = superClass.getDeclaredField("mSubtitleTextView")
                field.isAccessible = true
                val textView = field[this] as TextView?
                val typeface = ResourcesCompat.getFont(context, R.font.nanum_square_regular)
                textView?.typeface = typeface
                val layoutParams = generateDefaultLayoutParams()
                textView?.layoutParams = layoutParams
            }
        } catch (ignore: NoSuchFieldException) {
        } catch (ignore: IllegalAccessException) {
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        try {
            val superClass: Class<*>? = javaClass.superclass
            if (superClass != null) {
                val fieldTitle = superClass.getDeclaredField("mTitleTextView")
                fieldTitle.isAccessible = true
                val titleView = fieldTitle[this] as TextView?

                val fieldSubTitle = superClass.getDeclaredField("mSubtitleTextView")
                fieldSubTitle.isAccessible = true
                val subTitleView = fieldSubTitle[this] as TextView?
                titleView?.layout(
                    width / 2 - titleView.width / 2,
                    titleView.top,
                    width / 2 + titleView.width / 2,
                    titleView.bottom
                )
                subTitleView?.layout(
                    width / 2 - subTitleView.width / 2,
                    subTitleView.top,
                    width / 2 + subTitleView.width / 2,
                    subTitleView.bottom
                )
            }
        } catch (ignore: NoSuchFieldException) {
        } catch (ignore: IllegalAccessException) {
        }
    }

    override fun setNavigationIcon(resId: Int) {
        super.setNavigationIcon(resId)
        navigationIcon!!.setTint(Color.BLACK)
    }

    override fun generateDefaultLayoutParams(): LayoutParams? {
        val layoutParams =
            LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
        layoutParams.gravity = Gravity.CENTER
        return layoutParams
    }
}
