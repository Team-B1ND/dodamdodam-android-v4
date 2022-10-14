package kr.hs.dgsw.smartschool.data.repository

import android.webkit.MimeTypeMap
import kr.hs.dgsw.smartschool.data.datasource.FileUploadDataSource
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.Random
import javax.inject.Inject

class FileUploadRepositoryImpl @Inject constructor(
    private val fileUploadDataSource: FileUploadDataSource
) : FileUploadRepository {

    private lateinit var filePart: MultipartBody.Part

    override suspend fun uploadImg(file: File): String {
        setImageMultipartBodyList(file)
        return fileUploadDataSource.uploadFile(filePart)
    }

    private fun setImageMultipartBodyList(file: File) {
        val uploadName = "DODAM_FILE_${Random().nextInt(Int.MAX_VALUE)}"

        val extension = getExtension(file)
        val mediaType = getMediaType(extension)
        val requestBody: RequestBody = file.asRequestBody(mediaType.toMediaTypeOrNull())

        filePart = MultipartBody.Part.createFormData("file", "$uploadName.$extension", requestBody)
    }

    private fun getMediaType(extension: String): String {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)!!
    }

    private fun getExtension(file: File): String {
        val fileNameArray: Array<String> = file.name.split(".").toTypedArray()
        return fileNameArray[fileNameArray.size - 1]
    }
}
