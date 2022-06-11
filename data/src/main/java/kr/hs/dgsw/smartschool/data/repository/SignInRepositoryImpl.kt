package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.SignInDataSource
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource
): SignInRepository {
    override suspend fun signIn(signInRequest: SignInRequest) {
        signInDataSource.signIn(signInRequest)
    }
}