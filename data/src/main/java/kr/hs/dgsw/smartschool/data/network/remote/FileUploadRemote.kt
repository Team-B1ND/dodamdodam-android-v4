package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.FileUploadApi
import okhttp3.MultipartBody
import javax.inject.Inject

class FileUploadRemote @Inject constructor(
    override val api: FileUploadApi
) : BaseRemote<FileUploadApi>() {

    suspend fun uploadFile(file: MultipartBody.Part): String =
        api.uploadFile(file).data
}
