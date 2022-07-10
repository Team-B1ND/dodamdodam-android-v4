package kr.hs.dgsw.smartschool.data.repository

import android.util.Log
import kr.hs.dgsw.smartschool.data.datasource.SignUpDataSource
import kr.hs.dgsw.smartschool.domain.repository.SignUpRepository
import kr.hs.dgsw.smartschool.domain.request.SignUpRequest
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpDataSource
): SignUpRepository {
    override suspend fun signUp(signUpRequest: SignUpRequest): String {
        Log.d("TestTest", "signUp: repository")
        return signUpDataSource.signUp(signUpRequest)
    }

}