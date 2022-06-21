package kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOError
import java.io.IOException
import javax.inject.Inject

class DeleteBusApplyUseCase @Inject constructor(
    private val busRepository: BusRepository
){
    operator fun invoke(): Flow<Resource<String>> = flow {
        try{
            emit(Resource.Loading())
            val result = busRepository.
            emit(Resource.Success<>())
        }catch(e : HttpException){
            emit(Resource.Error<String>(e.localizedMessage ?: "AN unexpected error occured"))
        }catch (e: IOException){
            Resource.Error<String>("Couldn't reach server. Check your internet connection")
        }
    }

}