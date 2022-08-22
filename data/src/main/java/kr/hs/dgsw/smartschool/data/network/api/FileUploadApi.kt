package kr.hs.dgsw.smartschool.data.network.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FileUploadApi {
    @Multipart
    @POST("upload/image")
    suspend fun uploadImg(
        @Part image: MultipartBody.Part,
        @Part name: MultipartBody.Part
    ): Response<Any>
}
