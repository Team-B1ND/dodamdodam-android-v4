package kr.hs.dgsw.smartschool.domain.usecase.upload

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.io.File
import javax.inject.Inject

class UploadFileUseCase @Inject constructor(
    val repository: FileUploadRepository
) : UseCase<File, String>() {

    override operator fun invoke(params: File): Flow<Resource<String>> = execute {
        repository.uploadImg(params)
    }
}
