package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusInfo @Inject constructor(
    private val busRepository: BusRepository
<<<<<<< HEAD
){
    operator fun invoke(
        request: UpdateBusRequest
    ): Flow<Resource<String>> = flow{
        try{
            emit(Resource.Loading())
            busRepository.updateBusInfo(request)
            emit(Resource.Success<String>("버스 정보를 수정합니다"))
        }catch(e : HttpException){
            emit(Resource.Error<String>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<String>("Couldn't reach server. Check your internet connection")
        }
=======
) : BaseUseCase<Unit, String>() {
    override fun invoke(params: Unit): Flow<Resource<String>> = execute {
        busRepository.updateBusInfo()
>>>>>>> dev
    }
}