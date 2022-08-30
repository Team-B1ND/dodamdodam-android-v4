package kr.hs.dgsw.smartschool.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException

abstract class BaseUseCase<PR, R> {

    abstract operator fun invoke(params: PR): Flow<Resource<R>>

    fun execute(action: suspend () -> R): Flow<Resource<R>> = flow {
        try {
            emit(Resource.Loading<R>())
            val result = action.invoke()
            emit(Resource.Success<R>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<R>(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error<R>(Utils.NETWORK_ERROR_MESSAGE))
        }/* catch (e: Exception) {
            emit(Resource.Error<R>(e.message ?: "알 수 없는 오류가 발생했습니다."))
        }*/
    }
}
