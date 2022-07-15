package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBusList @Inject constructor(
    private val busRepository: BusRepository
){
    operator fun invoke(): Flow<Resource<List<BusByDate>>> = flow{
        try{
            emit(Resource.Loading())
            val result = busRepository.getBusList()
            emit(Resource.Success<List<BusByDate>>(result))
        }catch(e : HttpException){
            emit(Resource.Error<List<BusByDate>>(Utils.convertErrorBody(e)))
        }catch (e: IOException){
            Resource.Error<List<BusByDate>>(Utils.NETWORK_ERROR_MESSAGE)
        }
    }
}