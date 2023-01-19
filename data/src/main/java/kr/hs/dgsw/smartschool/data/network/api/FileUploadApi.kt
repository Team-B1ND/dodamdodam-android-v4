package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileUploadApi {
    @Multipart
    @POST(DodamUrl.UPLOAD)
    suspend fun uploadFile(
        @Part file: MultipartBody.Part,
    ): Response<String>
}
