package kr.hs.dgsw.smartschool.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.DELETE
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundData
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import retrofit2.http.PATCH


interface LostFoundApi {
    @GET("lostfound")
    fun getLostFound(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("type") type: String
    ): Response<LostFoundData>

    @GET("lostfound/comment")
    fun getLostFoundComment(
        @Query("lostfoundIdx") lostfoundIdx: Int
    ): Response<LostFoundData>

    @POST("lostfound")
    fun postCreateLostFound(
        @Body request: LostFoundRequest
    ): Response<Any>

    @POST("lostfound/comment")
    fun postLostFoundComment(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @PATCH("lostfound")
    fun putLostFound(
        @Body request: LostFoundRequest
    ): Response<Any>

    @PATCH("lostfound/comment")
    fun putLostFoundComment(
        @Body request: AddCommentRequest
    ): Response<Any>

    @DELETE("lostfound")
    fun deleteLostFound(
        @Query("idx") idx: Int
    ): Response<Any>

    @DELETE("lostfound/comment")
    fun deleteLostFoundComment(
        @Query("commentIdx") commentIdx: Int
    ): Response<Any>

    @GET("lostfound/find")
    fun getLostFoundSearch(
        @Query("title") title: String
    ): Response<LostFoundData>
}