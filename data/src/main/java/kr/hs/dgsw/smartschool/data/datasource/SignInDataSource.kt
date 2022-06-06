package kr.hs.dgsw.smartschool.data.datasource

import android.util.Log
import kr.hs.dgsw.smartschool.data.database.cache.SignInCache
import kr.hs.dgsw.smartschool.data.database.cache.TokenCache
import kr.hs.dgsw.smartschool.data.database.entity.SignInEntity
import kr.hs.dgsw.smartschool.data.mapper.SignInMapper
import kr.hs.dgsw.smartschool.data.network.remote.SignInRemote
import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.model.response.UserData
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import javax.inject.Inject

class SignInDataSource @Inject constructor(
    private val remote: SignInRemote,
    private val tokenCache: TokenCache,
    private val signInCache: SignInCache
 ) {

    suspend fun signIn(signInRequest: SignInRequest): kr.hs.dgsw.smartschool.domain.model.response.SignIn {
        return remote.signIn(signInRequest).data
    }
    suspend fun autoSignIn(): kr.hs.dgsw.smartschool.domain.model.response.SignIn {
        val token : String = tokenCache.getToken()
        Log.e("SignInDataSource","토큰을 가져옵니다 $token")
        val signInRequest :SignInRequest = signInCache.getData(token)
        Log.e("SignInDataSource","자동 로그인 요청을 보냅니다($signInRequest) 결과(${remote.signIn(signInRequest).data})")
        return remote.signIn(signInRequest).data
    }
}