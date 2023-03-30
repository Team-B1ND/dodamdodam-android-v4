package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LostFoundApi {
    @GET(DodamUrl.LOST_FOUND)
    suspend fun getLostFound(
        @Query("limit") limit: String,
        @Query("page") page: String,
        @Query("type") type: String
    ): Response<List<LostFound>>

    @GET(DodamUrl.LostFound.COMMENT)
    suspend fun getComment(
        @Query("lostFoundId") lostFoundId: Int
    ): Response<List<Comment>>

    @GET(DodamUrl.LostFound.SEARCH)
    suspend fun getLostFoundSearch(
        @Query("title") title: String
    ): Response<List<LostFound>>

    @GET(DodamUrl.LostFound.ALL)
    suspend fun getLostFoundAll(): Response<List<LostFound>>

    @GET(DodamUrl.LostFound.MY)
    suspend fun getMyLostFound(): Response<List<LostFound>>

    @POST(DodamUrl.LOST_FOUND)
    suspend fun postLostFound(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @POST(DodamUrl.LostFound.COMMENT)
    suspend fun postComment(
        @Body request: AddCommentRequest
    ): Response<Any>

    @PATCH(DodamUrl.LOST_FOUND)
    suspend fun patchLostFound(
        @Body request: LostFoundDataRequest
    ): Response<Any>

    @PATCH(DodamUrl.LostFound.COMMENT)
    suspend fun patchComment(
        @Body request: ModifyCommentRequest
    ): Response<Any>

    @DELETE(DodamUrl.LostFound.ID)
    suspend fun deleteLostFound(
        @Path("id") id: Int
    ): Response<Any>

    @DELETE(DodamUrl.LostFound.Comment.ID)
    suspend fun deleteComment(
        @Path("id") id: Int
    ): Response<Any>

    @GET(DodamUrl.LostFound.ID)
    suspend fun getLostFoundById(
        @Path("id") id: Int
    ): Response<LostFound>
}
