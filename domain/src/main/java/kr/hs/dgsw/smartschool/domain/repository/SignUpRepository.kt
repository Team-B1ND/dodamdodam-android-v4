package kr.hs.dgsw.smartschool.domain.repository

import dagger.hilt.android.qualifiers.ApplicationContext
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import javax.inject.Inject

interface SignUpRepository {
    suspend fun signUp(singUpRequest: SignUpRequest): String
}