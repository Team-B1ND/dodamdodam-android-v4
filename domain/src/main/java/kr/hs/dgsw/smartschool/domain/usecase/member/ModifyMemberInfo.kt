package kr.hs.dgsw.smartschool.domain.usecase.member

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyMemberInfo @Inject constructor(
    val repository: StudentRepository
) : UseCase<ModifyMemberInfo.Params, String>() {

    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.modifyMemberInfo(
            kr.hs.dgsw.smartschool.domain.param.member.ModifyMemberInfoParam(
                params.phone,
                params.email,
                params.url
            )
        )
    }

    data class Params(
        val phone: String,
        val email: String,
        val url: String
    )
}
