package kr.hs.dgsw.smartschool.data.network.interceptor

import kr.hs.dgsw.smartschool.data.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ErrorResponseInterceptor : Interceptor {

    private val TIME_OUT_ERROR = 400
    private val NOT_FOUND_ERROR = 404
    private val SERVER_ERROR = 500

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when(response.code) {
            TIME_OUT_ERROR -> throw Throwable(Constants.TIME_OUT_MESSAGE)
            NOT_FOUND_ERROR, SERVER_ERROR -> throw Throwable(Constants.SERVER_ERROR_MESSAGE)
            else -> return response
        }
    }
}