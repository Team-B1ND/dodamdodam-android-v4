package kr.hs.dgsw.smartschool.dodamdodam.util

import android.view.View
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import kotlin.math.abs

object ViewPagerUtils {
    fun getTransform(): CompositePageTransformer {
        val transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(10))

        transform.addTransformer { view: View, fl: Float ->
            val v = 1 - abs(fl)
            view.scaleY = 0.8f + v * 0.2f
        }

        return transform
    }
}
