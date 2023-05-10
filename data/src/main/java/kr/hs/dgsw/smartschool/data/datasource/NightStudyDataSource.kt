package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.NightStudyRemote
import kr.hs.dgsw.smartschool.data.network.request.nightstudy.NightStudyRequest
import kr.hs.dgsw.smartschool.data.network.response.nightstudy.NightStudyResponse
import javax.inject.Inject

class NightStudyDataSource @Inject constructor(
    override val remote: NightStudyRemote,
    override val cache: Any
) : BaseDataSource<NightStudyRemote, Any> {
    suspend fun applyNightStudy(request: NightStudyRequest): NightStudyResponse = remote.applyNightStudy(request)
    suspend fun deleteNightStudy(nightStudyId: Int): String = remote.deleteNightStudy(nightStudyId)
    suspend fun getMyNightStudy(): List<NightStudyResponse> = remote.getMyNightStudy()
}
