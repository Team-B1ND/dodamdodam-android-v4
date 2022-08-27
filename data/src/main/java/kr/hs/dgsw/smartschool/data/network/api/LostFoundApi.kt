package kr.hs.dgsw.smartschool.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Body
import retrofit2.http.DELETE
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundData
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPostRequest
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest


interface LostFoundApi {
    @GET("lost")
    fun getLostFound(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("type") type: Int
    ): Response<LostFoundData>

    @GET("lost/comment")
    fun getLostFoundComment(
        @Query("lostfoundIdx") lostfoundIdx: Int
    ): Response<LostFoundData>

    @POST("lost")
    fun postCreateLostFound(
        @Body request: LostFoundRequest
    ): Response<Any>

    @POST("lost/comment")
    fun postLostFoundComment(
        @Body request: LostFoundCommentPostRequest
    ): Response<Any>

    @PUT("lost")
    fun putLostFound(
        @Body request: LostFoundRequest
    ): Response<Any>

    @PUT("lost/comment")
    fun putLostFoundComment(
        @Body request: LostFoundCommentPutRequest
    ): Response<Any>

    @DELETE("lost")
    fun deleteLostFound(
        @Query("idx") idx: Int
    ): Response<Any>

    @DELETE("lost/comment")
    fun deleteLostFoundComment(
        @Query("commentIdx") commentIdx: Int
    ): Response<Any>

    @GET("lost/find")
    fun getLostFoundSearch(
        @Query("title") title: String
    ): Response<LostFoundData>
}