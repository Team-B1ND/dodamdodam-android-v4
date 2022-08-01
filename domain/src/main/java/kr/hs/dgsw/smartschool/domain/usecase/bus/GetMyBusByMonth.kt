package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMyBusByMonth @Inject constructor(
    private val busRepository: BusRepository
){
    operator fun invoke(
        request: MyBusByMonthRequest
    ): Flow<Resource<List<Bus>>> = flow{
        try{
            emit(Resource.Loading())
            val result = busRepository.getMyBusByMonth(request)
            emit(Resource.Success<List<Bus>>(result))
        }catch(e : HttpException){
            emit(Resource.Error<List<Bus>>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<List<Bus>>("Couldn't reach server. Check your internet connection")
        }
    }
}