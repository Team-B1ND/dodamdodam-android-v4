package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.SignInDataSource
import kr.hs.dgsw.smartschool.data.mapper.SignInMapper
import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.repository.SignInRepository
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInDataSource: SignInDataSource
): SignInRepository{
    override suspend fun SignIn(signInRequest: SignInRequest): SignIn {
        return signInDataSource.signIn(signInRequest)
    }

    override suspend fun autoSignIn(): SignIn {
        return signInDataSource.autoSignIn()
    }
}