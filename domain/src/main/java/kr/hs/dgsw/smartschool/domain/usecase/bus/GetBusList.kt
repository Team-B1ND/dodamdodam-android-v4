package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBusList @Inject constructor(
    private val busRepository: BusRepository
){
    operator fun invoke(): Flow<Resource<BusByDate>> = flow{
        try{
            emit(Resource.Loading())
            val result = busRepository.getBusList()
            emit(Resource.Success<BusByDate>(result))
        }catch(e : HttpException){
            emit(Resource.Error<BusByDate>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<BusByDate>("Couldn't reach server. Check your internet connection")
        }
    }
}