package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.EveningStudyRemote
import kr.hs.dgsw.smartschool.data.network.request.nightstudy.EveningStudyRequest
import kr.hs.dgsw.smartschool.data.network.response.eveningstudy.EveningStudyResponse
import javax.inject.Inject

class EveningStudyDataSource @Inject constructor(
    override val remote: EveningStudyRemote,
    override val cache: Any
) : BaseDataSource<EveningStudyRemote, Any> {
    suspend fun applyEveningStudy(request: EveningStudyRequest): EveningStudyResponse = remote.applyEveningStudy(request)
    suspend fun deleteEveningStudy(eveningStudyId: Int): String = remote.deleteEveningStudy(eveningStudyId)
    suspend fun getMyEveningStudy(): List<EveningStudyResponse> = remote.getMyEveningStudy()
}
