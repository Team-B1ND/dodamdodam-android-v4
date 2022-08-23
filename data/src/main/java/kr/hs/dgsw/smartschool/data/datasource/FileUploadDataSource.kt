package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.FileUploadRemote
import okhttp3.MultipartBody
import javax.inject.Inject

class FileUploadDataSource @Inject constructor(
    override val remote: FileUploadRemote,
    override val cache: Any
) : BaseDataSource<FileUploadRemote, Any>() {
    suspend fun uploadImg(image: MultipartBody.Part, name: MultipartBody.Part): String = remote.uploadImg(image, name)
}
