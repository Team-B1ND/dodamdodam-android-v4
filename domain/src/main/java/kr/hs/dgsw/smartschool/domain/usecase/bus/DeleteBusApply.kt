package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteBusApply @Inject constructor(
    private val busRepository: BusRepository
<<<<<<< HEAD
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
=======
) : BaseUseCase<Unit, String>() {
    override fun invoke(params: Unit): Flow<Resource<String>> = execute {
        busRepository.deleteBusApply()
>>>>>>> dev
    }
}