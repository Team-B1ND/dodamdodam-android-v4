package kr.hs.dgsw.smartschool.data.repository

import android.webkit.MimeTypeMap
import kr.hs.dgsw.smartschool.data.datasource.FileUploadDataSource
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.Random
import javax.inject.Inject

class FileUploadRepositoryImpl @Inject constructor(
    private val fileUploadDataSource: FileUploadDataSource
) : FileUploadRepository {

    private lateinit var imagePart: MultipartBody.Part
    private lateinit var namePart: MultipartBody.Part
    private lateinit var picture: Picture

    override suspend fun uploadImg(file: File): Picture {
        setImageMultipartBodyList(file)
        fileUploadDataSource.uploadImg(imagePart, namePart)
        return picture
    }

    private fun setImageMultipartBodyList(file: File) {
        val originalName = file.name.split(".")[0]
        val uploadName = "DA_IMG_${Random().nextInt(Int.MAX_VALUE)}"

        val extension = getExtension(file)
        val mediaType = getMediaType(extension)
        val imageBody: RequestBody = RequestBody.create(mediaType.toMediaTypeOrNull(), file)

        imagePart = MultipartBody.Part.createFormData("image", "$uploadName.$extension", imageBody)
        namePart = MultipartBody.Part.createFormData("name", uploadName)
        picture = Picture(originalName, uploadName, extension)
    }

    private fun getMediaType(extension: String): String {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)!!
    }

    private fun getExtension(file: File): String {
        val fileNameArray: Array<String> = file.name.split(".").toTypedArray()
        return fileNameArray[fileNameArray.size - 1]
    }
}
