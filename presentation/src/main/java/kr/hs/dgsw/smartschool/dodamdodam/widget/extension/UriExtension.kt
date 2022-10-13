package kr.hs.dgsw.smartschool.dodamdodam.widget.extension

import android.content.Context
import android.net.Uri
import java.io.File

fun Uri.getRealPathFromURI(context: Context): Uri {
    val cursor = context.contentResolver?.query(this, null, null, null, null)
    cursor?.moveToNext()
    val columnIndex = cursor?.getColumnIndex("_data")
    val picturePath = columnIndex?.let { cursor.getString(it) }
    cursor?.close()
    return Uri.fromFile(File(picturePath ?: ""))
}
