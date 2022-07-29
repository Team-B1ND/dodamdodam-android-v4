package kr.hs.dgsw.smartschool.domain.usecase.place

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.place.Place
import kr.hs.dgsw.smartschool.domain.repository.PlaceRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPlaceUseCase @Inject constructor(
    override val repository: PlaceRepository
) : BaseUseCase<PlaceRepository>() {
    operator fun invoke(): Flow<Resource<List<Place>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getAllPlace()
            emit(Resource.Success<List<Place>>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}