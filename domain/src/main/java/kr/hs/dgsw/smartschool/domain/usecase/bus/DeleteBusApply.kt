package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteBusApply @Inject constructor(
    private val busRepository: BusRepository
){
    operator fun invoke(
        idx : Int
    ): Flow<Resource<String>> = flow{
        try{
            emit(Resource.Loading())
            busRepository.deleteBusApply(idx)
            emit(Resource.Success<String>("버스를 신청 취소합니다"))
        }catch(e : HttpException){
            emit(Resource.Error<String>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<String>("Couldn't reach server. Check your internet connection")
        }
    }
}