package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import java.io.File

interface FileUploadRepository {
    suspend fun uploadImg(file: File): Picture
}
