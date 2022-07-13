package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.FileUploadApi
import okhttp3.MultipartBody
import javax.inject.Inject

class FileUploadRemote @Inject constructor(
    override val api: FileUploadApi
): BaseRemote<FileUploadApi>() {

    suspend fun uploadImg(image: MultipartBody.Part, name: MultipartBody.Part): String =
        api.uploadImg(image, name).message()

}