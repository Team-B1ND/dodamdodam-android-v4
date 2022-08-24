package kr.hs.dgsw.smartschool.data.datasource

import kr.hs.dgsw.smartschool.data.base.BaseDataSource
import kr.hs.dgsw.smartschool.data.network.remote.SignInRemote
import kr.hs.dgsw.smartschool.data.network.response.data.SignInData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import javax.inject.Inject

class SignInDataSource @Inject constructor(
    override val remote: SignInRemote,
    override val cache: Any
) : BaseDataSource<SignInRemote, Any> {
    suspend fun signIn(signInRequest: SignInRequest): SignInData =
        remote.signIn(signInRequest).data
}
