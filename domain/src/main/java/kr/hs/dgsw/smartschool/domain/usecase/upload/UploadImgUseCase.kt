package kr.hs.dgsw.smartschool.domain.usecase.upload

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class UploadImgUseCase @Inject constructor(
    override val repository: FileUploadRepository
) : BaseUseCase<FileUploadRepository>() {
    operator fun invoke(file: File): Flow<Resource<Picture>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.uploadImg(file)
            emit(Resource.Success<Picture>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}