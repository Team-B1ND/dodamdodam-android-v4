package kr.hs.dgsw.smartschool.domain.repository

import java.io.File

interface FileUploadRepository {
    suspend fun uploadImg(file: File): String
}
