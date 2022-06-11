package kr.hs.dgsw.smartschool.data.network.interceptor

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.data.datasource.AccountDataSource
import kr.hs.dgsw.smartschool.data.exception.TokenException
import kr.hs.dgsw.smartschool.domain.model.token.Token
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignInUseCase
import kr.hs.dgsw.smartschool.domain.usecase.token.TokenUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONException
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tokenUseCases: TokenUseCases,
    private val accountDataSource: AccountDataSource,
    private val coroutineScope: CoroutineScope
): Interceptor {

    private val TOKEN_ERROR = 410

    private lateinit var token: Token

    override fun intercept(chain: Interceptor.Chain): Response {
        setToken()

        val request =
            if (::token.isInitialized) chain.request().newBuilder()
                .header("x-access-token", token.token).build()
            else chain.request()

        val response = chain.proceed(request)
        if (response.code == 200) {
            coroutineScope.cancel()
            return response
        } else if (response.code == TOKEN_ERROR) {
            lateinit var r: Response
            try {
                r = makeTokenRefreshCall(request, chain)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            coroutineScope.cancel()
            return r
        }

        coroutineScope.cancel()
        return response
    }

    private fun makeTokenRefreshCall(request: Request, chain: Interceptor.Chain): Response {
        fetchToken()

        val newRequest = request.newBuilder().header("x-access-token", token.token).build()

        val another = chain.proceed(newRequest)

        return if (another.code == TOKEN_ERROR) {
            lateinit var r: Response
            try {
                r = login(request, chain)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            coroutineScope.cancel()
            r
        } else another
    }

    private fun login(request: Request, chain: Interceptor.Chain): Response {
        getTokenToLogin()

        val newRequest = request.newBuilder().header("x-access-token", token.token).build()

        val another = chain.proceed(newRequest)

        if (another.code == TOKEN_ERROR) {
            throw TokenException("세션이 만료되었습니다.")
        } else return another
    }

    private fun setToken() {
        tokenUseCases.getToken().onEach { result ->
            if (result is Resource.Success) {
                token = result.data!!
            }
        }.launchIn(coroutineScope)
    }

    private fun fetchToken() =
        tokenUseCases.updateNewToken().onEach { result ->
            if(result is Resource.Success) {
                token = result.data!!
            }
        }

    private fun getTokenToLogin() {
        val account = coroutineScope.async {
            accountDataSource.getAccount()
        }
        coroutineScope.launch {
            signInUseCase(account.await().id, account.await().pw, false).onEach {
                if (it is Resource.Success)
                    setToken()
            }.launchIn(coroutineScope)
        }
    }
}