package kr.hs.dgsw.smartschool.data.network.api

import kr.hs.dgsw.smartschool.data.network.response.Response
import kr.hs.dgsw.smartschool.data.network.response.song.SongResponse
import kr.hs.dgsw.smartschool.data.network.response.song.melon.SongChartResponse
import kr.hs.dgsw.smartschool.data.network.url.DodamUrl
import kr.hs.dgsw.smartschool.domain.param.song.SongRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SongApi {

    @POST(DodamUrl.WAKE_UP_SONG)
    suspend fun applySong(
        @Body request: SongRequest
    ): Response<Any>

    @DELETE(DodamUrl.WakeUpSong.My.ID)
    suspend fun deleteSong(
        @Path("id") id: Int
    ): Response<Any>

    @GET(DodamUrl.WakeUpSong.ALLOWED)
    suspend fun getAllowSong(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int
    ): Response<List<SongResponse>>

    @GET(DodamUrl.WakeUpSong.MY)
    suspend fun getMySong(): Response<List<SongResponse>>

    @GET(DodamUrl.WakeUpSong.PENDING)
    suspend fun getPendingSong(): Response<List<SongResponse>>

    @GET(DodamUrl.WakeUpSong.CHART)
    suspend fun getSongChart(): Response<List<SongChartResponse>>
}
