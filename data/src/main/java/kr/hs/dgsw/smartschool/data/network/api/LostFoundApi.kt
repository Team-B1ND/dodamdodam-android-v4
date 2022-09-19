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
    suspend fun getLostFound(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("type") type: String
    ): Response<List<LostFound>>

    @GET("lostfound/comment")
    suspend fun getComment(
        @Query("lostfoundIdx") lostfoundIdx: Int
    ): Response<List<Comment>>

    @GET("lostfound/search")
    suspend fun getLostFoundSearch(
        @Query("title") title: String
    ): Response<List<LostFound>>

    @GET("lostfound/my")
    suspend fun getMyLostFound(
    ): Response<List<LostFound>>

    @POST("lostfound")
    suspend fun postLostFound(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @POST("lostfound/comment")
    suspend fun postComment(
        @Body request: AddCommentRequest
    ): Response<Any>

    @PATCH("lostfound")
   suspend  fun patchLostFound(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @PATCH("lostfound/comment")
    suspend fun patchComment(
        @Body request: ModifyCommentRequest
    ): Response<Any>

    @DELETE("lostfound")
   suspend fun deleteLostFound(
        @Path("id") id : Int
    ): Response<Any>

    @DELETE("lostfound/comment")
   suspend fun deleteComment(
        @Path("id") id : Int
    ): Response<Any>

   @GET("lostfound/")
   suspend fun getLostFoundById(
       @Path("id") id : Int
   ): Response<LostFound>
}