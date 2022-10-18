package kr.hs.dgsw.smartschool.data.network.interceptor

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.exception.TokenException
import kr.hs.dgsw.smartschool.data.util.AppDispatchers
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.usecase.auth.LoginUseCase
import kr.hs.dgsw.smartschool.domain.usecase.token.TokenUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import retrofit2.HttpException
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenUseCases: TokenUseCases,
    private val accountDataSource: AccountDataSource,
    private val appDispatcher: AppDispatchers
) : Interceptor {
    private val TOKEN_ERROR = 401
    private val TOKEN_HEADER = "Authorization"

    private lateinit var token: Token

    override fun intercept(chain: Interceptor.Chain): Response {
        // 로컬 디비에서 토큰 가져와 저장.
        setToken()

        // 리퀘스트 생성 - 해터에 토큰 저장
        val request =
            if (::token.isInitialized) chain.request().newBuilder()
                .header("Authorization", "Bearer ${token.token}").build()
            else chain.request()

        // request 전송
        val response = chain.proceed(request)

        if (response.code == 200) {
            // 성공할 경우 response 반환
            return response
        } else if (response.code == TOKEN_ERROR) {
            // 토큰 에러 발생 시

            lateinit var r: Response

            try {
                r = makeTokenRefreshCall(request, chain)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return r
        }

        return response
    }

    private fun makeTokenRefreshCall(request: Request, chain: Interceptor.Chain): Response {
        try {
            // Refresh Token으로 새로운 AccessToken 적립
            fetchToken()
        } catch (e: HttpException) {
            // 어떤 이유로 오류 발생 시
            getTokenToLogin()
        }

        // request에 토큰을 붙여서 새로운 request 생성 -> 진행
        val newRequest = request.newBuilder().header(TOKEN_HEADER, "Bearer ${token.token}").build()
        val anotherResponse = chain.proceed(newRequest)

        return if (anotherResponse.code == TOKEN_ERROR) {
            // 만약 토큰 오류 발생 시 로그인
            lateinit var r: Response
            try {
                r = login(request, chain)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            r
        } else anotherResponse
    }

    private fun login(request: Request, chain: Interceptor.Chain): Response {
        // 로그인으로 토큰 교체
        getTokenToLogin()

        // request에 토큰을 붙여서 새로운 request 생성 -> 진행
        val newRequest = request.newBuilder().header(TOKEN_HEADER, "Bearer ${token.token}").build()
        val another = chain.proceed(newRequest)

        if (another.code == TOKEN_ERROR) {
            throw TokenException("세션이 만료되었습니다.")
        } else return another
    }

    private fun setToken() = runBlocking(appDispatcher.io) {
        tokenUseCases.getToken().let {
            token = it
        }
    }

    private fun fetchToken() = runBlocking(appDispatcher.io) {
        tokenUseCases.updateNewToken().let { token = it }
    }

    private fun getTokenToLogin() {
        runBlocking(appDispatcher.io) {
            // 계정을 DB에서 받아옴
            val account = accountDataSource.getAccount()
            loginUseCase(LoginUseCase.Params(account.id, account.pw, false)).onEach {
                if (it is Resource.Success) {
                    // 성공 시 로그인 딴에서 DB에 token 값을 저장하므로 DB에서 token을 가져오는 작업 수행
                    setToken()
                } else if (it is Resource.Error) {
                    throw TokenException("세션이 만료되었습니다.")
                }
            }
        }
    }
}
