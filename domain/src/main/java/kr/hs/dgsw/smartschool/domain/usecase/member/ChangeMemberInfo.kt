package kr.hs.dgsw.smartschool.domain.usecase.member

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.request.member.ModifyMemberInfoRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ChangeMemberInfo @Inject constructor(
    val repository: StudentRepository
) : BaseUseCase<ChangeMemberInfo.Params, String>() {

    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.changeMemberInfo(
            ModifyMemberInfoRequest(
                params.phone,
                params.email,
                params.profileImage
            )
        )
        "정보 변경에 성공했습니다."
    }

    data class Params(
        val memberId: String,
        val phone: String,
        val email: String,
        val profileImage: Picture
    )
}
