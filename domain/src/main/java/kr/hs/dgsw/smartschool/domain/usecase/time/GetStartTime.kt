package kr.hs.dgsw.smartschool.domain.usecase.time

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.time.Time
import kr.hs.dgsw.smartschool.domain.repository.TimeRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetStartTime @Inject constructor(
    override val repository: TimeRepository
) : BaseUseCase<TimeRepository>() {
    operator fun invoke(): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getStartTime()
            emit(Resource.Success<String>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error("서버에 도달 할 수 없습니다. 네트워크 상태를 확인해 주세요."))
        }
    }
}