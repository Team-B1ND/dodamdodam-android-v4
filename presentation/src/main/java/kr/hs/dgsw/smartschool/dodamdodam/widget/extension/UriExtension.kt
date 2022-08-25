package kr.hs.dgsw.smartschool.dodamdodam.widget.extension

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File

fun Uri.getRealPathFromURI(context: Context): Uri {
    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver?.query(this, filePathColumn, null, null, null)
    cursor?.moveToFirst()
    val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
    val picturePath = columnIndex?.let { cursor.getString(it) }
    cursor?.close()
    return Uri.fromFile(File(picturePath ?: ""))
}
