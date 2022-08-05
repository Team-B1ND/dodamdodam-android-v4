package kr.hs.dgsw.smartschool.domain.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException

abstract class BaseUseCase<PA, R> {

    abstract operator fun invoke(params: PA): Flow<Resource<R>>

    fun execute(action: suspend () -> R): Flow<Resource<R>> = flow {
        try {
            emit(Resource.Loading<R>())
            val result = action.invoke()
            emit(Resource.Success<R>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<R>(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error<R>(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}