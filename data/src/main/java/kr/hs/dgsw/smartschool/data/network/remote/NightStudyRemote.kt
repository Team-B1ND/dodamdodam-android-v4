package kr.hs.dgsw.smartschool.data.network.remote

import kr.hs.dgsw.smartschool.data.base.remote.BaseRemote
import kr.hs.dgsw.smartschool.data.network.api.NightStudyApi
import kr.hs.dgsw.smartschool.data.network.request.nightstudy.NightStudyRequest
import kr.hs.dgsw.smartschool.data.network.response.nightstudy.NightStudyResponse
import javax.inject.Inject

class NightStudyRemote @Inject constructor(
    override val api: NightStudyApi
) : BaseRemote<NightStudyApi>() {
    suspend fun applyNightStudy(request: NightStudyRequest): NightStudyResponse = api.applyNightStudy(request).data
    suspend fun deleteNightStudy(nightStudyId: Int): String = api.deleteNightStudy(nightStudyId).message
    suspend fun getMyNightStudy(): List<NightStudyResponse> = api.getMyNightStudy().data
}
