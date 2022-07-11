package kr.hs.dgsw.smartschool.domain.usecase.member

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.member.Student
import kr.hs.dgsw.smartschool.domain.repository.StudentRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMyInfo @Inject constructor(
    override val repository: StudentRepository
) : BaseUseCase<StudentRepository>() {

    operator fun invoke(): Flow<Resource<Student>> = flow {
        try {
            emit(Resource.Loading<Student>())
            val result = repository.getMyInfo()
            emit(Resource.Success<Student>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<Student>(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error<Student>(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}