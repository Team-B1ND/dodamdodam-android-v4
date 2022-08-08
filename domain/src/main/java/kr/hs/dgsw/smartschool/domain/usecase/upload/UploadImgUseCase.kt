package kr.hs.dgsw.smartschool.domain.usecase.upload

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.repository.FileUploadRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.io.File
import javax.inject.Inject

class UploadImgUseCase @Inject constructor(
    val repository: FileUploadRepository
) : BaseUseCase<File, Picture>() {
    override operator fun invoke(params: File): Flow<Resource<Picture>> = execute {
        repository.uploadImg(params)
    }
}