package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddBusApply @Inject constructor(
    private val busRepository: BusRepository
<<<<<<< HEAD
){
    operator fun invoke(): Flow<Resource<String>> = flow{
        try{
            emit(Resource.Loading())
            busRepository.addBusApply()
            emit(Resource.Success<String>("버스를 신청했습니다"))
        }catch(e : HttpException){
            emit(Resource.Error<String>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<String>("Couldn't reach server. Check your internet connection")
        }
=======
) : BaseUseCase<Unit, String>() {
    override operator fun invoke(params: Unit): Flow<Resource<String>> = execute {
        busRepository.addBusApply()
>>>>>>> dev
    }
}