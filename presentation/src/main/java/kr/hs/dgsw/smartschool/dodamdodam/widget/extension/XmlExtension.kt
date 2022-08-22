package kr.hs.dgsw.smartschool.dodamdodam.widget.extension

import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

fun ViewGroup.shortSnack(message: String?) {
    Snackbar.make(this, message.orEmpty(), Snackbar.LENGTH_SHORT).show()
}

fun ViewGroup.shortSnack(message: Int) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun ViewGroup.longSnack(message: String?) {
    Snackbar.make(this, message.orEmpty(), Snackbar.LENGTH_LONG).show()
}

fun ViewGroup.longSnack(message: Int) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
