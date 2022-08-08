package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddBus @Inject constructor(
    private val busRepository: BusRepository
<<<<<<< HEAD
){
    operator fun invoke(
        request: AddBusRequest
    ): Flow<Resource<String>> = flow{
=======
) {
    operator fun invoke(): Flow<Resource<String>> = flow{
>>>>>>> dev
        try{
            emit(Resource.Loading())
            busRepository.addBus(request)
            emit(Resource.Success<String>("버스를 추가했습니다"))
        }catch(e : HttpException){
            emit(Resource.Error<String>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<String>("Couldn't reach server. Check your internet connection")
        }
    }
}