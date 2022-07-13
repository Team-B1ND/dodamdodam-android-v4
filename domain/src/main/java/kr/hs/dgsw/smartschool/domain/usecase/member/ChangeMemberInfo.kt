package kr.hs.dgsw.smartschool.domain.usecase.member

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.request.MyInfoChangeRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChangeMemberInfo @Inject constructor(
    override val repository: StudentRepository
) : BaseUseCase<StudentRepository>() {

    operator fun invoke(params: Params): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            repository.changeMemberInfo(
                params.memberId,
                MyInfoChangeRequest(
                    params.phone,
                    params.email,
                    params.profileImage
                )
            )
            emit(Resource.Success<String>("프로필 수정에 성공하였습니다!"))
        } catch (e: HttpException) {
            emit(Resource.Error(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
        }
    }

    data class Params(
        val memberId: String,
        val phone: String,
        val email: String,
        val profileImage: Picture
    )
}