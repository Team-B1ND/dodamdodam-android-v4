package kr.hs.dgsw.smartschool.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.DELETE
import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import retrofit2.http.PATCH
import retrofit2.http.Path


interface LostFoundApi {
    @GET("lostfound")
    fun getLostFound(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("type") type: String
    ): Response<List<LostFound>>

    @GET("lostfound/comment")
    fun getComment(
        @Query("lostfoundIdx") lostfoundIdx: Int
    ): Response<List<Comment>>

    @GET("lostfound/search")
    fun getLostFoundSearch(
        @Query("title") title: String
    ): Response<List<LostFound>>

    @GET("lostfound/my")
    fun getMyLostFound(
    ): Response<List<LostFound>>

    @POST("lostfound")
    fun postLostFound(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @POST("lostfound/comment")
    fun postComment(
        @Body request: AddCommentRequest
    ): Response<Any>

    @PATCH("lostfound")
    fun patchLostFound(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @PATCH("lostfound/comment")
    fun patchComment(
        @Body request: ModifyCommentRequest
    ): Response<Any>

    @DELETE("lostfound")
    fun deleteLostFound(
        @Path("id") id : Int
    ): Response<Any>

    @DELETE("lostfound/comment")
    fun deleteComment(
        @Path("id") id : Int
    ): Response<Any>
}