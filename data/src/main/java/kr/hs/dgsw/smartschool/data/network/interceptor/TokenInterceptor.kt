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
    private val TOKEN_ERROR = 410

    private lateinit var token: Token

    override fun intercept(chain: Interceptor.Chain): Response {
        setToken()

        val request =
            if (::token.isInitialized) chain.request().newBuilder()
                .header("Authorization", "Bearer ${token.token}").build()
            else chain.request()

        val response = chain.proceed(request)
        if (response.code == 200) {
            return response
        } else if (response.code == TOKEN_ERROR) {
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
            fetchToken()
        } catch (e: HttpException) {
            getTokenToLogin()
        }

        val newRequest = request.newBuilder().header("Authorization", "Bearer ${token.token}").build()
        val another = chain.proceed(newRequest)

        return if (another.code == TOKEN_ERROR) {
            lateinit var r: Response
            try {
                r = login(request, chain)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            r
        } else another
    }

    private fun login(request: Request, chain: Interceptor.Chain): Response {
        getTokenToLogin()

        val newRequest = request.newBuilder().header("Authorization", "Bearer ${token.token}").build()

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
            val account = accountDataSource.getAccount()
            loginUseCase(LoginUseCase.Params(account.id, account.pw, false)).onEach {
                if (it is Resource.Success)
                    setToken()
            }
        }
    }
}
