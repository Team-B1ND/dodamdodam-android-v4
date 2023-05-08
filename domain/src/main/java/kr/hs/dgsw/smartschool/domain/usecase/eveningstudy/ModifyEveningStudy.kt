// package kr.hs.dgsw.smartschool.domain.usecase.eveningstudy
//
// import kotlinx.coroutines.flow.Flow
// import kr.hs.dgsw.smartschool.domain.base.UseCase
// import kr.hs.dgsw.smartschool.domain.model.eveningstudy.EveningStudyItem
// import kr.hs.dgsw.smartschool.domain.repository.EveningStudyRepository
// import kr.hs.dgsw.smartschool.domain.util.Resource
// import javax.inject.Inject
//
// class ModifyEveningStudy @Inject constructor(
//    private val eveningStudyRepository: EveningStudyRepository
// ) : UseCase<ModifyEveningStudy.Params, EveningStudyItem>() {
//
//    override fun invoke(params: Params): Flow<Resource<EveningStudyItem>> = execute {
//        eveningStudyRepository.modifyEveningStudy(
//            params.startOutDate,
//            params.endOutDate,
//            params.reason,
//            params.outId,
//            params.isNeedPhone,
//            params.phoneReason
//        )
//    }
//
//    data class Params(
//        val startOutDate: String,
//        val endOutDate: String,
//        val reason: String,
//        val outId: Int,
//        val isNeedPhone: Boolean,
//        val phoneReason: String
//    )
// }
