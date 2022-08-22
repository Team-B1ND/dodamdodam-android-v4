package kr.hs.dgsw.smartschool.data.base.remote

import com.google.gson.GsonBuilder
import kr.hs.dgsw.smartschool.data.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

abstract class DAuthRemote<SV> : BaseRemote<SV>() {
    protected fun <T> createApi(service: Class<T>): T {
        return RETROFIT.create(service)
    }

    private val RETROFIT: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.AUTH_SERVER_HOST)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
        .callbackExecutor(Executors.newSingleThreadExecutor())
        .build()

    private val client: OkHttpClient
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpBuilder = OkHttpClient().newBuilder().addInterceptor(interceptor)
            return okHttpBuilder.build()
        }
}
