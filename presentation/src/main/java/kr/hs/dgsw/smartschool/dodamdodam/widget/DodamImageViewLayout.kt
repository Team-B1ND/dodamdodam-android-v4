package kr.hs.dgsw.smartschool.dodamdodam.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.net.Uri
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.item_dodam_image.view.*
import kr.hs.dgsw.smartschool.dodamdodam.R

class DodamImageViewLayout : FrameLayout {

    private var mContext: Context? = null
    private var attrs: AttributeSet? = null
    private var styleAttr: Int? = null
    private var view = View.inflate(context, R.layout.item_dodam_image, null)

    constructor(context: Context) : super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    @SuppressLint("Recycle")
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        this.mContext = context
        this.attrs = attrs
        this.styleAttr = defStyleAttr

        addView(view)

        val typedArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.DodamImageViewLayout
        )

        val drawable = typedArray.getDrawable(R.styleable.DodamImageViewLayout_src) ?: return

        dodamImageView1.putImage(drawable)
        textView.visibility = View.GONE
        linearLayout1.visibility = View.GONE
        frameLayout1.visibility = View.GONE
    }

    @SuppressLint("SetTextI18n")
    fun putImages(vararg urls: String) {
        when (val count = urls.size) {
            1 -> {
                dodamImageView1.putImage(urls[0])
                textView.visibility = View.GONE
                linearLayout1.visibility = View.GONE
                frameLayout1.visibility = View.GONE
            }
            2 -> {
                dodamImageView1.putImage(urls[0])
                dodamImageView2.putImage(urls[1])
                textView.visibility = View.GONE
                linearLayout1.visibility = View.VISIBLE
                frameLayout1.visibility = View.GONE
            }
            3 -> {
                dodamImageView1.putImage(urls[0])
                dodamImageView2.putImage(urls[1])
                dodamImageView3.putImage(urls[2])
                textView.visibility = View.GONE
                linearLayout1.visibility = View.VISIBLE
                frameLayout1.visibility = View.VISIBLE
            }
            else -> {
                dodamImageView1.putImage(urls[0])
                dodamImageView2.putImage(urls[1])
                dodamImageView3.putImage(urls[2])
                textView.text = "+${count - 3}"
                textView.visibility = View.VISIBLE
                linearLayout1.visibility = View.VISIBLE
                frameLayout1.visibility = View.VISIBLE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun putImages(vararg uris: Uri) {
        when (val count = uris.size) {
            1 -> {
                dodamImageView1.putImage(uris[0])
                textView.visibility = View.GONE
                linearLayout1.visibility = View.GONE
                frameLayout1.visibility = View.GONE
            }
            2 -> {
                dodamImageView1.putImage(uris[0])
                dodamImageView2.putImage(uris[1])
                textView.visibility = View.GONE
                linearLayout1.visibility = View.VISIBLE
                frameLayout1.visibility = View.GONE
            }
            3 -> {
                dodamImageView1.putImage(uris[0])
                dodamImageView2.putImage(uris[1])
                dodamImageView3.putImage(uris[2])
                textView.visibility = View.GONE
                linearLayout1.visibility = View.VISIBLE
                frameLayout1.visibility = View.VISIBLE
            }
            else -> {
                dodamImageView1.putImage(uris[0])
                dodamImageView2.putImage(uris[1])
                dodamImageView3.putImage(uris[2])
                textView.text = "+${count - 3}"
                textView.visibility = View.VISIBLE
                linearLayout1.visibility = View.VISIBLE
                frameLayout1.visibility = View.VISIBLE
            }
        }
    }
}