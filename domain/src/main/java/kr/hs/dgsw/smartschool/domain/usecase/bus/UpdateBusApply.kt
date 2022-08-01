package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UpdateBusApply @Inject constructor(
    private val busRepository: BusRepository
){
    operator fun invoke(
        request: UpdateBusApplyRequest
    ): Flow<Resource<String>> = flow{
        try{
            emit(Resource.Loading())
            busRepository.updateBusApply(request)
            emit(Resource.Success<String>("버스 신청을 수정합니다"))
        }catch(e : HttpException){
            emit(Resource.Error<String>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<String>("Couldn't reach server. Check your internet connection")
        }
    }
}